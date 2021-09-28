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
@RequestMapping(value = "/api/auth")
@Api(tags = "Auth")
public class AuthController {

    private final JwtAuthProvider authProvider;

    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<AuthReponseTransferObject> tryToAuthUser(@RequestBody AuthRequestTransferObject authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),
                authRequestDto.getPassword()));

        String token = authProvider.getToken(authRequestDto.getEmail());
        return ResponseEntity.ok(new AuthReponseTransferObject(token));
    }


}
