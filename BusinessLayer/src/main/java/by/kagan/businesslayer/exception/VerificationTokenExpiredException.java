package by.kagan.businesslayer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VerificationTokenExpiredException extends RuntimeException{
    public VerificationTokenExpiredException(){
        super("Verification token expired");
    }
}
