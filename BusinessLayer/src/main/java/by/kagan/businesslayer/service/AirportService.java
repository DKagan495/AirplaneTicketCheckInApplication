package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.Airport;
import by.kagan.businesslayer.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    @Transactional
    public Airport create(Airport airport){
        airportRepository.save(airport);
        return airport;
    }
}
