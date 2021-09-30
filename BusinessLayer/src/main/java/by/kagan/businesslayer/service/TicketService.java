package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    private final UserService userService;

    private Principal principal;

    public Ticket create(Ticket ticket){
        ticket.setUserId(userService.getUserByEmail(principal.getName()).getId());
        ticketRepository.save(ticket);
        return ticket;
    }
}
