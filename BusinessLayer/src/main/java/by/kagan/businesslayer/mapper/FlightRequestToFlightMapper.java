package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.dto.request.FlightRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FlightRequestToFlightMapper {

    @Mappings({
            @Mapping(target = "airportFrom", ignore = true),
            @Mapping(target = "airportTo", ignore = true),
            @Mapping(target = "tickets", ignore = true)
    })
    Flight map(FlightRequest request);
}
