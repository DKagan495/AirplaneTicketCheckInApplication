package by.kagan.businesslayer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "flight_id")
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "user_id",
    insertable = false,
    updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id",
    insertable = false,
    updatable = false)
    private Flight flight;
}
