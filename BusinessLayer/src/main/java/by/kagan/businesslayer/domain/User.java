package by.kagan.businesslayer.domain;

import by.kagan.businesslayer.auth.enumeration.Role;
import by.kagan.businesslayer.validator.annotaion.Match;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//TODO: чтобы постгрес позволил создать таблицу user - ее следует назвать "user". В данном случае: @Table(name = "\"user\"")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Date dateOfBirth;
<<<<<<< HEAD

=======
//    TODO: пустые строки между полями в целом, особенно - когда над след полем используются аннотации
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c
    @Column(name = "acc_enabled")
    private boolean isAccountEnabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Ticket> tickets;
}
