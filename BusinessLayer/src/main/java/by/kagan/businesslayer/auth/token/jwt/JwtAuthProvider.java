package by.kagan.businesslayer.auth.token.jwt;

import by.kagan.businesslayer.auth.token.model.Account;
import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Log
public class JwtAuthProvider {

    @Value("$(jwt.authkey)")
    private String key;



    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    public boolean isTokenLegacy(String token){
        Date today = Date.from(Instant.now());
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(today);
    }

    public String getToken(String email){
        Date expDate = Date.from(LocalDate.now().plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(key);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }
}
