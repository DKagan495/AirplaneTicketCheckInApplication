package by.kagan.businesslayer.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightRequest {
    private Long airportFromId;

    private Long airportToId;

    private Date date;

    private int ticketsLeft;
}
