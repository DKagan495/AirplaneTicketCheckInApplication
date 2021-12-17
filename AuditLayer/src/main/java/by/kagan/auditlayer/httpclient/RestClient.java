package by.kagan.auditlayer.httpclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class RestClient {
    private final RestTemplate restTemplate;

    public <T>ResponseEntity<T> getBusinessLayerResponse(@RequestBody(required = false)
                                                         final URI uri,
                                                         final HttpMethod httpMethod,
                                                         final HttpEntity<String> httpEntity,
                                                         ParameterizedTypeReference<T> responseType){
        return restTemplate.exchange(uri, httpMethod, httpEntity, responseType);
    }

    public URI createURI(String businessUrl, HttpServletRequest httpServletRequest) throws URISyntaxException {
        URI uri = new URI(businessUrl);
        String requestUrl = httpServletRequest.getRequestURI();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .path(requestUrl)
                .query(
                        httpServletRequest.getQueryString()
                )
                .build(true)
                .toUri();
        return uri;
    }

    public HttpHeaders createHeader(String contentTypeHeader, HttpServletRequest httpServletRequest, String authorization, String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(contentTypeHeader, httpServletRequest.getHeader(contentTypeHeader));
        httpHeaders.set(authorization, token);
        return httpHeaders;
    }
}
