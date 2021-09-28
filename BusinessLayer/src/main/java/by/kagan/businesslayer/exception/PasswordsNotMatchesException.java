
package by.kagan.businesslayer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordsNotMatchesException extends RuntimeException{
    public PasswordsNotMatchesException(){
        super("Password not match with confirmation");
    }
}