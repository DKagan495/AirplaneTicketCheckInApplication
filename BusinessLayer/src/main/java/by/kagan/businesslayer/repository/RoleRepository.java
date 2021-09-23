package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
