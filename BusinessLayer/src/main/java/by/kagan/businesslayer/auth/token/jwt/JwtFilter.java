package by.kagan.businesslayer.auth.token.jwt;

import by.kagan.businesslayer.auth.token.model.Account;
import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final String AUTHORIZATION = "Authorization";

    final JwtAuthProvider provider;

    final AccountAuthorizationService authorizationService;

    @Value("$(jwt.authkey)")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String email, token;
        email = getEmailFromRequest(request);
        token = request.getHeader(AUTHORIZATION).substring(7);
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            Account account = (Account) authorizationService.loadUserByUsername(email);
            if(provider.validateToken(token, account)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getEmailFromRequest(HttpServletRequest request){
        final String BEARER_AND_TOKEN = request.getHeader(AUTHORIZATION);
        return provider.getUsername(BEARER_AND_TOKEN).substring(7);
    }
}
