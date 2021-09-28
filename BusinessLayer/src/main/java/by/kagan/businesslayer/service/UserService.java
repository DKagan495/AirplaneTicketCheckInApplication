package by.kagan.businesslayer.service;

import by.kagan.businesslayer.auth.enumeration.Role;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public User create(User user) throws PasswordsNotMatchesException {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setAccountEnabled(false);

        userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }


    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }


    public User loadUserById(Long id) throws UserNotFoundException {
       return userRepository.findById(id).
               orElseThrow(()->new UserNotFoundException("User with this id not exists"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User with this email not exists"));
    }


<<<<<<< HEAD

=======
//TODO: интерфейсная модель сервисов - в топку
//TODO: обработка юзера как ентити и как участника авторизации - разные сервисы
public interface UserService {
//    TODO: название можно сократить до save, лучше - create как более широкое понятие. Принимтаь на вход DTO - плохая идея
    User saveUserDto(UserDto userDto) throws PasswordsNotMatchesException;

//    TODO: создаваемые/обновленные сущности стоит возвращать из сервисных методов, даже если результат далее использован не будет
    void createVerificationToken(User user, String token);

//    TODO: из названия не следует логика метода
    VerificationToken loadVerificationToken(String token) throws VerificationTokenNotFoundException, VerificationTokenExpiredException;
    //    TODO: создаваемые/обновленные сущности стоит возвращать из сервисных методов, даже если результат далее использован не будет
    void updateUser(Long id, UserDto userDto);

//    TODO: getUserByEmail
    User loadUserByEmail(String email);
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c
}
