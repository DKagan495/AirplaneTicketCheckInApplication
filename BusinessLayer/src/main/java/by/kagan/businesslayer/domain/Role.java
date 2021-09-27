package by.kagan.businesslayer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
//TODO: позволяет создание новых ролей через БД. Если нет пермишнов и дискретной ролевой модели - ситуация неконтролируема и необрабатываема
//TODO: не рекомендую использование GrantedAuthority в контексте доменного объекта
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
