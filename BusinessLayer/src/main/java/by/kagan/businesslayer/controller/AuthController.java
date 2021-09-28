package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtAuthProvider;
import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.AuthReponseTransferObject;
import by.kagan.businesslayer.dto.AuthRequestTransferObject;
import by.kagan.businesslayer.dto.UserRequest;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.VerificationTokenExpiredException;
import by.kagan.businesslayer.mapper.UserToUserDtoMapper;
import by.kagan.businesslayer.service.AuthService;
import by.kagan.businesslayer.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Api(tags = "Auth")
public class AuthController {

    private final AccountAuthorizationService authorizationService;

    private final AuthService authService;

    private final UserService userService;

    private final JwtAuthProvider authProvider;

    private final AuthenticationManager authenticationManager;

    private final ApplicationEventPublisher eventPublisher;



    @PostMapping(value = "/login")
    public ResponseEntity<AuthReponseTransferObject> tryToAuthUser(@RequestBody AuthRequestTransferObject authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),
                authRequestDto.getPassword()));

        String token = authProvider.getToken(authRequestDto.getEmail());
        return ResponseEntity.ok(new AuthReponseTransferObject(token));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody UserRequest entityUserRequest, BindingResult bindingResult, final HttpServletRequest request){
        System.out.println("Before errors checking");
        if(bindingResult.hasErrors() || bindingResult.hasFieldErrors()){
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }
            User user = UserToUserDtoMapper.unMap(entityUserRequest);
            userService.create(user);
            user.setId(userService.getUserByEmail(user.getEmail()).getId());
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new AfterCompleteRegistrationEvent(user, request.getLocale(), appUrl));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping(value = "/signupconfirmation")
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
