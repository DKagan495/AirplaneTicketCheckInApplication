package by.kagan.businesslayer.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private String airportFrom;

    private String airportTo;

    private Date date;

    private int ticketsLeft;
}
