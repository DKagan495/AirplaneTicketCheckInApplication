package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.dto.request.AirportRequest;
import by.kagan.businesslayer.mapper.AirportRequestToAirportMapper;
import by.kagan.businesslayer.service.AirportService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Airport Information")
@RequestMapping(value = "/api/user/airports")
public class AirportController {
    private final AirportService airportService;

    private final AirportRequestToAirportMapper toAirportMapper;

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createAirport(@RequestBody AirportRequest request){
        airportService.create(toAirportMapper.map(request));

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
