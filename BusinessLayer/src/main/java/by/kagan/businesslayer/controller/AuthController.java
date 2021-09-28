package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtAuthProvider;
import by.kagan.businesslayer.dto.AuthReponseTransferObject;
import by.kagan.businesslayer.dto.AuthRequestTransferObject;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Api(tags = "Authentication and Authorization")
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
