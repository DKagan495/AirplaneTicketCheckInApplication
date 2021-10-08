package by.kagan.businesslayer.messaging;

import by.kagan.businesslayer.auth.token.model.Account;
import by.kagan.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MessageFilter extends GenericFilterBean {

    private final MessageProducer producer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.getPrincipal() instanceof Account) {
            producer.send(AuditInfoMessage
                    .builder()
                    .email(auth.getName())
                    .endpoint(request.getServletContext().getContextPath())
                    .requestTime(LocalDateTime.now())
                    .responseCode(((HttpServletResponse) response).getStatus())
                    .build()
            );
        }

        chain.doFilter(request, response);
    }
}
