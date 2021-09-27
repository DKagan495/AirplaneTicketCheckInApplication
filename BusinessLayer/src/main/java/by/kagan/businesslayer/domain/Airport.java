package by.kagan.businesslayer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
//TODO: название таблицы - в ед.ч.
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    TODO: Почему set?
    @OneToMany(mappedBy = "airportFrom")
    private Set<Flight> flightsFrom;

    @OneToMany(mappedBy = "airportTo")
    private Set<Flight> flightsTo;

}
