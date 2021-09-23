package by.kagan.businesslayer.service;

import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUserDto(UserDto userDto) throws PasswordsNotMatchesException;
}
