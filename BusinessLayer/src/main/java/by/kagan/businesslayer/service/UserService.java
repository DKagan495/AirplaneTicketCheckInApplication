package by.kagan.businesslayer.service;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.UserNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User loadUserByEmailAndPassword(String email, String password) throws UserNotFoundException;
    void saveUserDto(UserDto userDto) throws PasswordsNotMatchesException;
}
