package by.kagan.businesslayer.service;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.VerificationTokenExpiredException;
import by.kagan.businesslayer.exception.VerificationTokenNotFoundException;
import org.springframework.stereotype.Service;

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
}
