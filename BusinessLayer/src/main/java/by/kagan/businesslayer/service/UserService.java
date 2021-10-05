package by.kagan.businesslayer.service;

import by.kagan.businesslayer.auth.enumeration.Role;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final EntityManager entityManager;

    @Transactional
    @Cacheable(value = "user")
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setEnabled(false);

        userRepository.save(user);
        return user;
    }


    public List<User> getAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);

        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("onlyDeleted", isDeleted);

        List<User> all = userRepository.findAll();

        session.disableFilter("deletedUserFilter");

        return all;
    }

    @Cacheable(value = "user")
    public User loadUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).
                orElseThrow(()->new UserNotFoundException("User with this id not exists"));
    }

    @Cacheable(value = "user")
    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User with this email not exists"));
    }

    @Transactional
    @CachePut(value = "user", key = "#user.email")
    public User update(Long id, User user){
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    @Transactional
    @CacheEvict(value = "user")
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
