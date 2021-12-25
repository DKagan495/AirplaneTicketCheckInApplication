package by.kagan.auditlayer.controller;

import by.kagan.auditlayer.httpclient.RestClient;
import by.kagan.auditlayer.service.MessageService;
import by.kagan.message.AuditInfoMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class AuditEndpoint {
    private final MessageService messageService;

    private static final String TOKEN = "Token";
    private static final String REQUEST_BODY = "";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String BUSINESS_URL = "http://localhost:7777";

    @GetMapping("/allaudit")
    public List<AuditInfoMessage> getAllAudit(){
        return messageService.getAll();
    }
}
