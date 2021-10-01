package by.kagan.businesslayer.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    TODO: есть ли необходимость двусторонней связи? Должна ли быть eager? Рекомендую хранить id отдельным полем
    //Если будет необходимость получить все полеты из конкретного аэропорта?(двусторонняя связь)

    @Column(name = "airport_from_id", nullable = false)
    private Long airportFromId;

    @Column(name = "airport_to_id", nullable = false)
    private Long airportToId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from_id",
    insertable = false,
    updatable = false)
    private Airport airportFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to_id",
    insertable = false,
    updatable = false)
    private Airport airportTo;

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets;

    @Column(name = "flight_date")
    private Date date;

    private int ticketsLeft;
}
