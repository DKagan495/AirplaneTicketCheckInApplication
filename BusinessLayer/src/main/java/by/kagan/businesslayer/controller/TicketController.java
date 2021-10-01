package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.dto.request.TicketRequest;
import by.kagan.businesslayer.mapper.TicketRequestToTicketMapper;
import by.kagan.businesslayer.service.TicketService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/ticket")
@Api(tags = "Ticket operations")
public class TicketController {
    private final TicketService ticketService;

    private final TicketRequestToTicketMapper toTicketMapper;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> getTicket(TicketRequest request, Principal principal){
        Ticket ticket = toTicketMapper.map(request);
        ticketService.create(principal.getName(), ticket);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
