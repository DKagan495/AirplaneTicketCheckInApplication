package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.dto.request.TicketRequest;

public class TicketRequestToTicketMapper {
    public static Ticket map(TicketRequest request){
        Ticket ticket = new Ticket();
        ticket.setFlightId(request.getFlightId());
        return ticket;
    }
}
