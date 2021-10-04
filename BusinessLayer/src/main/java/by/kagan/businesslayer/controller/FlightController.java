package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.dto.request.FlightRequest;
import by.kagan.businesslayer.dto.response.FlightResponse;
import by.kagan.businesslayer.mapper.FlightRequestToFlightMapper;
import by.kagan.businesslayer.mapper.FlightToFlightDtoMapper;
import by.kagan.businesslayer.service.FlightService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Flights information")
@RequestMapping(value = "/api/user/flights")
public class FlightController {
    private final FlightService flightService;

    private final FlightToFlightDtoMapper toFlightDtoMapper;

    private final FlightRequestToFlightMapper toFlightMapper;

    @GetMapping
    public List<FlightResponse> getAll(){
        return flightService
                .getAll()
                .stream()
                .collect(ArrayList::new,
                         (list, flight)->list.add(toFlightDtoMapper.map(flight)),
                         ArrayList::addAll);
    }

    @GetMapping("/{id}")
    public FlightResponse get(@PathVariable Long id){
        return toFlightDtoMapper.map(flightService.getById(id));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightResponse> create(@RequestBody FlightRequest request){
        return new ResponseEntity<>(toFlightDtoMapper.map(flightService.create(toFlightMapper.map(request))), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<FlightResponse> update(@PathVariable Long id, FlightRequest request){
        return new ResponseEntity<>(toFlightDtoMapper.map(flightService.update(id, toFlightMapper.map(request))), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        flightService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
