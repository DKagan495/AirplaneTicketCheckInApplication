package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUser(User user);
}
