package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.domain.Airport;
import by.kagan.businesslayer.dto.request.AirportRequest;
import by.kagan.businesslayer.dto.response.AirportDto;
import by.kagan.businesslayer.dto.response.FlightResponse;
import by.kagan.businesslayer.mapper.AirportRequestToAirportMapper;
import by.kagan.businesslayer.mapper.AirportToAirportDtoMapper;
import by.kagan.businesslayer.service.AirportService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Airport Information")
@RequestMapping(value = "/api/user/airports")
public class AirportController {
    private final AirportService airportService;

    private final AirportRequestToAirportMapper toAirportMapper;

    private final AirportToAirportDtoMapper toAirportDtoMapper;

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportDto> create(@RequestBody AirportRequest request){
        AirportDto airportDto = toAirportDtoMapper.map(airportService.create(toAirportMapper.map(request)));
        return new ResponseEntity<>(airportDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<AirportDto> getAll(){
        return airportService
                .getAll()
                .stream()
                .collect(ArrayList::new,
                         ((airportDtos, airport) -> airportDtos.add(toAirportDtoMapper.map(airport))),
                         ArrayList::addAll);
    }

    public AirportDto getById(Long id){
        return toAirportDtoMapper.map(airportService.getById(id));
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<AirportDto> update(@PathVariable Long id, @RequestBody AirportRequest airportRequest){
        AirportDto airportDto = toAirportDtoMapper.map(airportService.update(id, toAirportMapper.map(airportRequest)));
        return new ResponseEntity<>(airportDto, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        airportService.kick(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
