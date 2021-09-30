package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AirportRepository extends JpaRepository<Airport, Long> {

}
