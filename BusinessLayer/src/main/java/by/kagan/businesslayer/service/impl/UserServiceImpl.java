package by.kagan.businesslayer.service.impl;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.VerificationTokenExpiredException;
import by.kagan.businesslayer.exception.VerificationTokenNotFoundException;
import by.kagan.businesslayer.repository.RoleRepository;
import by.kagan.businesslayer.repository.TokenRepository;
import by.kagan.businesslayer.repository.UserRepository;
import by.kagan.businesslayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static by.kagan.businesslayer.mapper.UserToUserDtoMapper.unMap;

@Service
@RequiredArgsConstructor
//TODO: Transactional над классом - избыточно
@Transactional
public class UserServiceImpl implements UserService {
//TODO: модификторы доступа
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder encoder;
    final AuthenticationProvider provider;
    final TokenRepository tokenRepository;

    @Override
    public User saveUserDto(UserDto userDto) throws PasswordsNotMatchesException {
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            throw new PasswordsNotMatchesException("Password not matches with confirmation.");
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
//        TODO: статический испорт методов - зло
        User user = unMap(userDto);
//        TODO: Литералы - в константы, если это не логи/кастомные тексты ошибок
//        TODO: роли стоит выносить в енам.
//        TODO: зачем репа ролей?
        user.setRole(roleRepository.findByRole("ROLE_USER"));
        user.setAccountEnabled(false);

//        TODO: разные логические блоки отделять пустой строкой. Здесь: настройка сущности и ее сохранение
        userRepository.save(user);
        return user;
    }

    @Override
    //        TODO: сервис должен работать с определенным уровнем сущности. Или везде на вход дто, или везде - энтити
    public void createVerificationToken(User user, String token){
        VerificationToken verificationToken = new VerificationToken(user, token);
        //        TODO: разные логические блоки отделять пустой строкой. Здесь: создание сущности и ее сохранение.
        //         Общее замечание по коду, даже где коммента нет
        tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken loadVerificationToken(String token) throws VerificationTokenNotFoundException, VerificationTokenExpiredException {
//         TODO: исопльзовать методы optional вместо if-блоков, либо не использоват optional
        Optional<VerificationToken> hypoteseToken = tokenRepository.findByToken(token);
        if(hypoteseToken.isEmpty()){
//            TODO: если литерал сообщения стандартные - можно инкапсулировать его в конструктор, а не передавать из вне
            throw new VerificationTokenNotFoundException("Verification token not found");
        }
        if(hypoteseToken.get().getExpiryDate().before(Date.from(Instant.now()))){
            throw new VerificationTokenExpiredException("Verification token is expired");
        }
       return hypoteseToken.get();
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = unMap(userDto);
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public User loadUserByEmail(String email) {
//        TODO: userRepository.findByEmail().ifPresent(); || userRepository.findByEmail().orElseThrow();
        if(userRepository.findByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User with this email not exists");
        }
        return userRepository.findByEmail(email).get();
    }
}
