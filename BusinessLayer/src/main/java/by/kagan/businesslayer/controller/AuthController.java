package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtAuthProvider;
import by.kagan.businesslayer.dto.AuthTransferObjectResponse;
import by.kagan.businesslayer.dto.AuthTransferObjectRequest;
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

//TODO: какая мотивация такого названия метода?
    @PostMapping
    public ResponseEntity<AuthTransferObjectResponse> tryToAuthUser(@RequestBody AuthTransferObjectRequest authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),
                authRequestDto.getPassword()));

        String token = authProvider.getToken(authRequestDto.getEmail());
        return ResponseEntity.ok(new AuthTransferObjectResponse(token));
    }


}
