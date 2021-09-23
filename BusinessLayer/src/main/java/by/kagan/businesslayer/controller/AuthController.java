package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtAuthProvider;
import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.AuthReponseTransferObject;
import by.kagan.businesslayer.dto.AuthRequestTransferObject;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.service.impl.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Api(tags = "Auth")
public class AuthController {

    final AccountAuthorizationService authorizationService;
    final UserServiceImpl userService;
    final JwtAuthProvider authProvider;


    @GetMapping(value = "/test")
    public String hello(Principal principal) throws UserNotFoundException {
        System.out.println(principal.getName());
        return userService.loadUserByEmailAndPassword(principal.getName(), "string").getFirstName();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthReponseTransferObject> tryToAuthUser(@RequestBody AuthRequestTransferObject authRequestDto) throws UserNotFoundException {
        System.out.println(authRequestDto.getEmail() + "this is authreqdtoemail");
        User tempUser = userService.auth(authRequestDto.getEmail(), authRequestDto.getPassword());
        String token = authProvider.getToken(tempUser.getEmail());
        System.out.println(token);
        //Account account = (Account) authorizationService.loadUserByUsername(authRequestDto.getEmail());
        if(token == null){
            return null;
        }
       // System.out.println(Jwts.parser().parseClaimsJws(token).getBody().getSubject());
        return ResponseEntity.ok(new AuthReponseTransferObject(token));
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserDto userDto) {
        try{
            userService.saveUserDto(userDto);
        } catch (PasswordsNotMatchesException exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
