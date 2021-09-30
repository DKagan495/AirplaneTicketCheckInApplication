package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    private final UserService userService;



    @Transactional
    public Ticket create(String email, Ticket ticket){
        ticket.setUserId(userService.getUserByEmail(email).getId());
        ticketRepository.save(ticket);
        return ticket;
    }
}
