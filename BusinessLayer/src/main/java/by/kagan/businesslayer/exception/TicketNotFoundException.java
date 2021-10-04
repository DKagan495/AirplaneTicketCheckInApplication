package by.kagan.businesslayer.exception;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(Long id){
        super("Ticket with id=" + id + " not exists");
    }
}
