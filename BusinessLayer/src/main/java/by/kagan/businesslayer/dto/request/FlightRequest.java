package by.kagan.businesslayer.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class FlightRequest {

    @NotBlank(message = "AirportFrom is blank")
    private Long airportFromId;

    @NotBlank(message = "AirportTo is blank")
    private Long airportToId;

    @Future(message = "Flight date cannot be in past or now")
    private Date date;

    @NotBlank(message = "Tickets amount is blank")
    @Min(value = 1, message = "Tickets amount less than 0")
    private int ticketsLeft;
}
