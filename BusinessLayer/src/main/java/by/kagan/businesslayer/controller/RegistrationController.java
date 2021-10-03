package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtProvider;
import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.request.UserRequest;
import by.kagan.businesslayer.mapper.UserRequestToUserMapper;
import by.kagan.businesslayer.service.UserService;
import by.kagan.businesslayer.validator.NameValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signup")
@Api(tags = "Sign Up and Account Verifying")
@Slf4j
public class RegistrationController {
    private final NameValidator nameValidator;

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    private final UserRequestToUserMapper toUserMapper;

    private final JwtProvider provider;

    @InitBinder("userRequest")
    private void initBinder(WebDataBinder binder){
        binder.setValidator(nameValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> signup(@Valid @RequestBody UserRequest userRequest, final HttpServletRequest request){

        User user = toUserMapper.map(userRequest);
        userService.create(user);

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new AfterCompleteRegistrationEvent(user, request.getLocale(), appUrl));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping(value = "/verify")
    public ResponseEntity<HttpStatus> confirmAccount(@RequestParam String token){
        String email = provider.getUsername(token);
        if(!provider.validateToken(token)){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserByEmail(email);

        user.setAccountEnabled(true);
        userService.updateUser(user);

        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
}
