package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

//    TODO: create?
    public Flight save(Flight flight){
        flightRepository.save(flight);
        return flight;
    }

}
