package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUser(User user);
}
