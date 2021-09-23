package by.kagan.businesslayer.auth.token.jwt;

import by.kagan.businesslayer.auth.token.model.Account;
import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    final JwtAuthProvider provider;

    final AccountAuthorizationService authorizationService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("filt");
        String token = getTokenFromRequest((HttpServletRequest) request);
        logger.info("fil");
        System.out.println(token + "tfr");
        if (token != null && provider.validateToken(token)) {
            String userLogin = provider.getUsername(token);
            Account userDetails = (Account) authorizationService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
