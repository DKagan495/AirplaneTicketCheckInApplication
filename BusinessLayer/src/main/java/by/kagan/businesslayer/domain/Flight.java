package by.kagan.businesslayer.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.metamodel.model.domain.internal.AbstractIdentifiableType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airport_from_id")
    private Airport airportFrom;

    @ManyToOne
    @JoinColumn(name = "airport_to_id")
    private Airport airportTo;

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets;

    @Column(name = "fight_date")
    private Date date;
}
