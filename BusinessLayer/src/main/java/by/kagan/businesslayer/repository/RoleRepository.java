package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.domain.Role;
import org.springframework.data.repository.CrudRepository;
//TODO: зачем нужен?
//TODO: рекомендую везде использвоать одного предка. Например - PagingAndSortingRepository/JpaRepository.
// То, что сейчас достаточно CRUD не гарантирует необходимости сортировок или чего-л. еще в дальнейшем
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
