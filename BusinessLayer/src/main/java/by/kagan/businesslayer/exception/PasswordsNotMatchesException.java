package by.kagan.businesslayer.exception;

//TODO: почти всегда есть смысл наследовать кастомные эксепшны от RuntimeException
// Если причина в неверных действиях юзера - это RuntimeException
public class PasswordsNotMatchesException extends Exception{
    public PasswordsNotMatchesException(String message){
        super(message);
    }
}
