package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    private final UserService userService;



    @Cacheable(value = "ticket")
    @Transactional
    public Ticket create(String email, Ticket ticket){
        ticket.setUserId(userService.getUserByEmail(email).getId());
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllByEmail(String email){
        User user = userService.getUserByEmail(email);
        return ticketRepository.findAllByUser(user);
    }

    @CachePut(value = "ticket", key = "#ticket.id")
    @Transactional
    public Ticket update(Long id, Ticket ticket){
        ticket.setId(id);
        ticketRepository.save(ticket);
        return ticket;
    }

    @CacheEvict(value = "ticket")
    @Transactional
    public void delete(Long id){
        ticketRepository.deleteById(id);
    }
}
