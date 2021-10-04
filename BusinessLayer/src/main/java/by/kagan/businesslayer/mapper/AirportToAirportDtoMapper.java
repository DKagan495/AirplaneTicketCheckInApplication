package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Airport;
import by.kagan.businesslayer.dto.response.AirportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportToAirportDtoMapper {
    AirportDto map(Airport airport);
}
