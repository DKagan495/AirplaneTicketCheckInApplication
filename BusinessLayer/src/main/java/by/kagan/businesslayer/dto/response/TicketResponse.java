package by.kagan.businesslayer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private String airportFromName;

    private String airportToName;

    private String userFirstName;

    private String userLastName;

    private Date date;

}
