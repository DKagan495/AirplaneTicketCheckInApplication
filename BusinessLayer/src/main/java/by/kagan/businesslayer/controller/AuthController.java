package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtProvider;
import by.kagan.businesslayer.dto.response.AuthResponse;
import by.kagan.businesslayer.dto.AuthRequest;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Api(tags = "Authentication and Authorization")
public class AuthController {
    private final JwtProvider authProvider;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),
                authRequestDto.getPassword()));

        String token = authProvider.getToken(authRequestDto.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
