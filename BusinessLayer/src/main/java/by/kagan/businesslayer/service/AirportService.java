package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Airport;
import by.kagan.businesslayer.exception.AirportNotFoundException;
import by.kagan.businesslayer.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    @Transactional
    @Cacheable(value = "airport")
    public Airport create(Airport airport){
        airportRepository.save(airport);
        return airport;
    }

    public List<Airport> getAll(){
        return airportRepository.findAll();
    }

    @Transactional
    public Airport update(Long id, Airport airport){
        airport.setId(id);
        airportRepository.save(airport);
        return airport;
    }

    public Airport getById(Long id){
        return airportRepository.findById(id).orElseThrow(()->new AirportNotFoundException(id));
    }

    @Transactional
    public void kick(Long id){
        airportRepository.deleteById(id);
    }
}
