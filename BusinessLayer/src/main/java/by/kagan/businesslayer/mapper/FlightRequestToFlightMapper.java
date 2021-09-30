package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.dto.request.FlightRequest;

public class FlightRequestToFlightMapper {

    public static Flight map(FlightRequest request){
        Flight flight = new Flight();
        flight.setAirportFromId(request.getAirportFromId());
        flight.setAirportToId(request.getAirportToId());
        flight.setDate(request.getDate());
        flight.setTicketsLeft(request.getTicketsLeft());

        return flight;
    }
}
