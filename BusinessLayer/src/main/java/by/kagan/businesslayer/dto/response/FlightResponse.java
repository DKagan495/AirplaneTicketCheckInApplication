package by.kagan.businesslayer.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class FlightResponse {
    private String airportFrom;

    private String airportTo;

    private Date date;

    private int ticketsLeft;
}
