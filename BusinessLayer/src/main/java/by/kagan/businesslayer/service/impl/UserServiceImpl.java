package by.kagan.businesslayer.service.impl;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.repository.UserRepository;
import by.kagan.businesslayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    public User loadUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        if(userRepository.findByEmailAndPassword(email, password).isEmpty()){
            throw new UserNotFoundException("User with this email and password not exists. Bad credentials.");
        }

        return userRepository.findByEmailAndPassword(email, password).get();
    }
}
