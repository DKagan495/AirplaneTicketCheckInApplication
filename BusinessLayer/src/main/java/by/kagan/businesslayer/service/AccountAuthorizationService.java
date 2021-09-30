package by.kagan.businesslayer.auth.token.service;

import by.kagan.businesslayer.auth.token.model.Account;
import by.kagan.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//TODO: разбиение по пакетам производится через группировку по уровням логики, а не по области месту применения класса
@Service
@RequiredArgsConstructor
public class AccountAuthorizationService implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userRepository.findByEmail(username).isEmpty()){
            throw new UsernameNotFoundException("This email is not exists");
        }
        return new Account(userRepository.findByEmail(username).get());
    }
}
