package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtAuthProvider;
import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.AuthReponseTransferObject;
import by.kagan.businesslayer.dto.AuthRequestTransferObject;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.exception.VerificationTokenNotFoundException;
import by.kagan.businesslayer.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static by.kagan.businesslayer.mapper.UserToUserDtoMapper.map;

@RestController
@RequiredArgsConstructor
@Api(tags = "Auth")
public class AuthController {

    final AccountAuthorizationService authorizationService;

    final UserServiceImpl userService;

    final JwtAuthProvider authProvider;

    final AuthenticationManager authenticationManager;

    final ApplicationEventPublisher eventPublisher;


    @GetMapping(value = "/test")
    public String hello(Principal principal){
        System.out.println(principal.getName());
        return authorizationService.loadUserByUsername(principal.getName()).toString();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthReponseTransferObject> tryToAuthUser(@RequestBody AuthRequestTransferObject authRequestDto) throws UserNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        } catch (BadCredentialsException exception) {
            exception.printStackTrace();
        }
        String token = authProvider.getToken(authRequestDto.getEmail());
        return ResponseEntity.ok(new AuthReponseTransferObject(token));
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserDto userDto, final HttpServletRequest request) {
        try{
            User user = userService.saveUserDto(userDto);
            user.setId(userService.loadUserByEmail(user.getEmail()).getId());
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new AfterCompleteRegistrationEvent(user, request.getLocale(), appUrl));
        } catch (PasswordsNotMatchesException exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //fix problem with roles
    @GetMapping(value = "/signupconfirmation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> confirmAccount(@RequestParam("token") String token, WebRequest request){
        VerificationToken verificationToken;
        try{
           verificationToken = userService.loadVerificationToken(token);
        } catch (VerificationTokenNotFoundException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        User user = verificationToken.getUser();
        user.setAccountEnabled(true);
        UserDto toUpdateEnabledStatusUserDto = map(user);
        userService.updateUser(user.getId(), toUpdateEnabledStatusUserDto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
}
