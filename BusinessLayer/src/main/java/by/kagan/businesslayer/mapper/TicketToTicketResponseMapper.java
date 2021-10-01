package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.dto.response.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketToTicketResponseMapper {

    @Mappings({
           @Mapping(target = "airportFromName", source = "flight.airportFrom.name"),
            @Mapping(target = "airportToName", source = "flight.airportTo.name"),
            @Mapping(target = "userFirstName", source = "user.firstName"),
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "date", source = "flight.date")
    })
    TicketResponse map(Ticket ticket);
}
