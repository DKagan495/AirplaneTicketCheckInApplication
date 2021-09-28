package by.kagan.businesslayer.exception;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordsNotMatchesException extends RuntimeException{
    public PasswordsNotMatchesException(){
        super("Password not match with confirmation");
=======
//TODO: почти всегда есть смысл наследовать кастомные эксепшны от RuntimeException
// Если причина в неверных действиях юзера - это RuntimeException
public class PasswordsNotMatchesException extends Exception{
    public PasswordsNotMatchesException(String message){
        super(message);
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c
    }
}
