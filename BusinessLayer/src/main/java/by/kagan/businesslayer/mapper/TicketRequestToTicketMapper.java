package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Ticket;
import by.kagan.businesslayer.dto.request.TicketRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketRequestToTicketMapper {

    @Mappings({
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "flight", ignore = true)
    })
    Ticket map(TicketRequest request);
}
