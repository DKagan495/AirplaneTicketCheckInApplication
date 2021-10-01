package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Airport;
import by.kagan.businesslayer.dto.request.AirportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AirportRequestToAirportMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "flightsFrom", ignore = true),
            @Mapping(target = "flightsTo", ignore = true)
    })
    Airport map(AirportRequest request);
}
