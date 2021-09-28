package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserEntityObjectRequest;
import by.kagan.businesslayer.exception.VerificationTokenExpiredException;
import by.kagan.businesslayer.mapper.RequestToUserMapper;
import by.kagan.businesslayer.mapper.UserToUserDtoMapper;
import by.kagan.businesslayer.service.AuthService;
import by.kagan.businesslayer.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signup")
@Api(tags = "Sign Up and Account Verifying")
public class RegistrationController {

    private final AuthService authService;

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;


    @PostMapping
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody UserEntityObjectRequest entityUserEntityObjectRequest, BindingResult bindingResult, final HttpServletRequest request){
        if(bindingResult.hasErrors() || bindingResult.hasFieldErrors()){
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }
        User user = RequestToUserMapper.map(entityUserEntityObjectRequest);
        userService.create(user);
        user.setId(userService.getUserByEmail(user.getEmail()).getId());
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new AfterCompleteRegistrationEvent(user, request.getLocale(), appUrl));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping(value = "/verify")
    public ResponseEntity<HttpStatus> confirmAccount(@RequestParam String token){
        VerificationToken verificationToken = authService.loadVerificationToken(token);

        if(verificationToken.getExpiryDate().before(Date.from(Instant.now()))){
            throw new VerificationTokenExpiredException();
        }

        User user = verificationToken.getUser();
        user.setAccountEnabled(true);

        userService.updateUser(user);

        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
}
