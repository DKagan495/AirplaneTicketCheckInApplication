package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.dto.response.FlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightToFlightDtoMapper {
    @Mappings({
            @Mapping(target = "airportFrom", source = "airportFrom.name"),
            @Mapping(target = "airportTo", source = "airportTo.name")
    })
    FlightResponse map(Flight flight);
}
