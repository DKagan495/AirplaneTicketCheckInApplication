package by.kagan.businesslayer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
//    TODO: пустые строки между полями в целом, особенно - когда над след полем используются аннотации
    @Column(name = "acc_enabled")
    private boolean isAccountEnabled;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Ticket> tickets;;
}
