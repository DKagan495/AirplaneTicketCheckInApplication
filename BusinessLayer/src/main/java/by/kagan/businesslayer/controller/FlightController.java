package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.enumeration.Role;
import by.kagan.businesslayer.dto.request.FlightRequest;
import by.kagan.businesslayer.dto.response.FlightResponse;
import by.kagan.businesslayer.mapper.FlightRequestToFlightMapper;
import by.kagan.businesslayer.mapper.FlightToFlightResponseMapper;
import by.kagan.businesslayer.service.AirportService;
import by.kagan.businesslayer.service.FlightService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public List<FlightResponse> getAll(){
        return flightService.getAllFlights().stream().collect(ArrayList::new, (list, flight)->list.add(FlightToFlightResponseMapper.map(flight)), ArrayList::addAll);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createFlight(@RequestBody FlightRequest request){
        flightService.create(FlightRequestToFlightMapper.map(request));

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
