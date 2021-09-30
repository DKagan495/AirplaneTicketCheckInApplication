package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: аннотация @Repository
public interface FlightRepository extends JpaRepository <Flight, Long> {

}
