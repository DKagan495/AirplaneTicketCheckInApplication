package by.kagan.businesslayer.auth.token.jwt;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class JwtProvider {

    private static final String KEY = "sometimes";


    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getToken(String email){
        Date expDate = Date.from(LocalDate.now().plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(KEY);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException |
                UnsupportedJwtException |
                MalformedJwtException |
                SignatureException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
