package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.exception.FlightNotFoundException;
import by.kagan.businesslayer.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @Transactional
    @Cacheable(value = "flight")
    public Flight create(Flight flight){
        flightRepository.save(flight);
        return flight;
    }

    public List<Flight> getAll(){
        return flightRepository.findAll();
    }

    @Cacheable(value = "flight")
    public Flight getById(Long id){
       return flightRepository.findById(id).orElseThrow(()->new FlightNotFoundException(id));
    }

    @Transactional
    @CachePut(value = "flight", key = "#flight.id")
    public Flight update(Long id, Flight flight){
        flight.setId(id);
        flightRepository.save(flight);
        return flight;
    }

    @CacheEvict(value = "flight")
    @Transactional
    public void delete(Long id){
        flightRepository.deleteById(id);
    }
}
