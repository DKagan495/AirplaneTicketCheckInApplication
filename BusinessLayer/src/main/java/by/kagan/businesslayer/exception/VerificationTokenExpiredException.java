package by.kagan.businesslayer.exception;

public class VerificationTokenExpiredException extends Exception{
    public VerificationTokenExpiredException(String message){
        super(message);
    }
}
