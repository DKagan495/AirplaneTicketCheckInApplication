package by.kagan.businesslayer.exception;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(Long id){
        super("Airport with id=" + id + " not found");
    }
}
