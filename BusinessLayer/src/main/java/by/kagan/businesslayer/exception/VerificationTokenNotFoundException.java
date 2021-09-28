package by.kagan.businesslayer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VerificationTokenNotFoundException extends RuntimeException{
    public VerificationTokenNotFoundException(String message) {
        super(message);
    }
}
