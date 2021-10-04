package by.kagan.businesslayer.service;

import by.kagan.businesslayer.auth.enumeration.Role;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Transactional
    @Cacheable(value = "user")
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setAccountEnabled(false);

        userRepository.save(user);
        return user;
    }


    public List<User> loadAllUsers() {
        return userRepository.findAll();
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
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
