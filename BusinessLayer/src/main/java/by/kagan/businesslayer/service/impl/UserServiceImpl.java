package by.kagan.businesslayer.service.impl;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.domain.Role;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.UserNotFoundException;
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
import static by.kagan.businesslayer.mapper.UserToUserDtoMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

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
        User user = unMap(userDto);
        user.setRole(roleRepository.findByRole("ROLE_USER"));
        user.setAccountEnabled(false);
        userRepository.save(user);
        return user;
    }


    public void createVerificationToken(User user, String token){
        System.out.println("Зашли в метод");
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken loadVerificationToken(String token) throws VerificationTokenNotFoundException {
        if(tokenRepository.findByToken(token).isEmpty()){
            throw new VerificationTokenNotFoundException("Verification token not found");
        }
       return tokenRepository.findByToken(token).get();
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = unMap(userDto);
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public User loadUserByEmail(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User with this email not exists");
        }
        return userRepository.findByEmail(email).get();
    }
}
