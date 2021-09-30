package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.dto.request.AirportRequest;
import by.kagan.businesslayer.dto.request.FlightRequest;
import by.kagan.businesslayer.mapper.AirportRequestToAirportMapper;
import by.kagan.businesslayer.mapper.FlightRequestToFlightMapper;
import by.kagan.businesslayer.service.AirportService;
import by.kagan.businesslayer.service.FlightService;
import by.kagan.businesslayer.service.TicketService;
import by.kagan.businesslayer.service.UserService;
import by.kagan.businesslayer.validator.NameValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Admin Panel")
@RequestMapping(value = "/api/admin")
public class AdminController {
    private final UserService userService;

    private final FlightService flightService;

    private final AirportService airportService;


    @PostMapping(value = "/flights/create")
    public ResponseEntity<HttpStatus> createFlight(FlightRequest request){
        flightService.create(FlightRequestToFlightMapper.map(request));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/airports/create")
    public ResponseEntity<HttpStatus> createAirport(AirportRequest request){
        airportService.create(AirportRequestToAirportMapper.map(request));

        return ResponseEntity.ok(HttpStatus.OK);
    }



}
