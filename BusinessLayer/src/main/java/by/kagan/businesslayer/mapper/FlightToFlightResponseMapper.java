package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.Flight;
import by.kagan.businesslayer.dto.response.FlightResponse;

public class FlightToFlightResponseMapper {

    public static FlightResponse map(Flight flight){
        FlightResponse flightResponse = new FlightResponse();
        flightResponse.setAirportFrom(flight.getAirportFrom().getName());
        flightResponse.setAirportTo(flight.getAirportTo().getName());
        flightResponse.setDate(flight.getDate());
        flightResponse.setTicketsLeft(flight.getTicketsLeft());
        return flightResponse;
    }
}
