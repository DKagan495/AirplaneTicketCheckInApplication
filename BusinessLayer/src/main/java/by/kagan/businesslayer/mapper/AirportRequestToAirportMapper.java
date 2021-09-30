package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Airport;
import by.kagan.businesslayer.dto.request.AirportRequest;

public class AirportRequestToAirportMapper {
    public static Airport map(AirportRequest request){
        Airport airport = new Airport();
        airport.setName(request.getName());
        return airport;
    }
}
