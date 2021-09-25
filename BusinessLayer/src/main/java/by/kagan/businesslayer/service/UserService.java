package by.kagan.businesslayer.service;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.VerificationTokenNotFoundException;
import org.springframework.stereotype.Service;


public interface UserService {
    User saveUserDto(UserDto userDto) throws PasswordsNotMatchesException;
    void createVerificationToken(User user, String token);
    VerificationToken loadVerificationToken(String token) throws VerificationTokenNotFoundException;
    void updateUser(Long id, UserDto userDto);
    User loadUserByEmail(String email);
}
