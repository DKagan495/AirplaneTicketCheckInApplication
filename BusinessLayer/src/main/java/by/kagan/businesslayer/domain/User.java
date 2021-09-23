package by.kagan.businesslayer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Ticket> tickets;
}
