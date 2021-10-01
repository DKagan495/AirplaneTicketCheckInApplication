package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.exception.FlightNotFoundException;
import by.kagan.businesslayer.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @Transactional
    public Flight create(Flight flight){
        flightRepository.save(flight);
        return flight;
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight getById(Long id){
       return flightRepository.findById(id).orElseThrow(()->new FlightNotFoundException(id));
    }
}
