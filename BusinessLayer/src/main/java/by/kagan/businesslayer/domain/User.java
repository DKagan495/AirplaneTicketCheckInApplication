package by.kagan.businesslayer.domain;

import by.kagan.businesslayer.auth.enumeration.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATE \"user\" SET deleted = true, enabled = false WHERE id=?")
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "onlyDeleted", type = "boolean"))
@Filter(name = "deletedUserFilter", condition = "deleted = :onlyDeleted")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Date dateOfBirth;

    @Column(name = "enabled")
    private boolean enabled;

    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Ticket> tickets;
}
