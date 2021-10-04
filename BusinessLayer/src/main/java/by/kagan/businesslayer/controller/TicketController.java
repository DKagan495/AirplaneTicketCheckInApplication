package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.dto.request.TicketRequest;
import by.kagan.businesslayer.dto.response.TicketResponse;
import by.kagan.businesslayer.mapper.TicketRequestToTicketMapper;
import by.kagan.businesslayer.mapper.TicketToTicketResponseMapper;
import by.kagan.businesslayer.service.TicketService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/tickets")
@Api(tags = "Ticket operations")
public class TicketController {
    private final TicketService ticketService;

    private final TicketRequestToTicketMapper toTicketMapper;

    private final TicketToTicketResponseMapper toTicketResponseMapper;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResponse> create(TicketRequest request, Principal principal){
        Ticket ticket = toTicketMapper.map(request);
        ticketService.create(principal.getName(), ticket);

        return new ResponseEntity<>(toTicketResponseMapper.map(ticket), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<TicketResponse> getAll(){
        return ticketService
                .getAll()
                .stream()
                .collect(ArrayList::new,
                         (list, ticket)->list.add(toTicketResponseMapper.map(ticket)),
                         ArrayList::addAll);
    }

    @GetMapping(value = "/my")
    public List<TicketResponse> getCurrentUserTickets(Principal principal){
        return ticketService
                .getAllByEmail(principal.getName())
                .stream()
                .collect(ArrayList::new,
                         (list, ticket)->list.add(toTicketResponseMapper.map(ticket)),
                         ArrayList::addAll);
    }



    @PatchMapping(value = "/{id}")
    public ResponseEntity<TicketResponse> update(@PathVariable Long id, @RequestBody TicketRequest request){
        Ticket updatedTicket = ticketService.update(id, toTicketMapper.map(request));

        return new ResponseEntity<>(toTicketResponseMapper.map(updatedTicket), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        ticketService.delete(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
