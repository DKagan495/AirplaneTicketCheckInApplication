package by.kagan.businesslayer.service.impl;

import by.kagan.businesslayer.domain.Role;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.repository.RoleRepository;
import by.kagan.businesslayer.repository.UserRepository;
import by.kagan.businesslayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder encoder;

    @Override
    public User loadUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        if(userRepository.findByEmailAndPassword(email, password).isEmpty()){
            throw new UserNotFoundException("User with this email and password not exists. Bad credentials.");
        }

        return userRepository.findByEmailAndPassword(email, password).get();
    }

    @Override
    public void saveUserDto(UserDto userDto) throws PasswordsNotMatchesException {
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            throw new PasswordsNotMatchesException("Password not matches with confirmation.");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(roleRepository.findByRole("ROLE_USER"));
        System.out.println(user.getRole().getRole());
        userRepository.save(user);
    }


    public User auth(String email, String password) throws UserNotFoundException {
        if(userRepository.findByEmailAndPassword(email, password).isEmpty()){
            throw new UserNotFoundException("Bad credentials");
        }
        return userRepository.findByEmailAndPassword(email, encoder.encode(password)).get();
    }
}
