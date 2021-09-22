package by.kagan.businesslayer.auth.token.jwt;

import by.kagan.businesslayer.auth.token.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtAuthProvider {

    @Value("$(jwt.authkey)")
    private String key;

    public Claims getAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getConcreteClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = getAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String getUsername(String token){
        return getConcreteClaim(token, Claims::getSubject);
    }

    public boolean isTokenLegacy(String token){
        Date checkExpDate = getConcreteClaim(token, Claims::getExpiration);
        return Date.from(Instant.now()).after(checkExpDate);
    }

    public String getToken(String email){
        Date expDate = Date.from(LocalDate.now().plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(key);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(key))
                .compact();
    }
    public boolean validateToken(String token, Account account){
        return !(isTokenLegacy(token)) && account.getUsername().equals(getUsername(token));
    }
}
