package by.kagan.businesslayer.service.impl;

import by.kagan.businesslayer.auth.enumeration.Role;
import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.EntityUserRequest;
import by.kagan.businesslayer.exception.PasswordsNotMatchesException;
import by.kagan.businesslayer.exception.VerificationTokenExpiredException;
import by.kagan.businesslayer.exception.VerificationTokenNotFoundException;
import by.kagan.businesslayer.mapper.UserToUserDtoMapper;
import by.kagan.businesslayer.repository.TokenRepository;
import by.kagan.businesslayer.repository.UserRepository;
import by.kagan.businesslayer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    final UserRepository userRepository;
    final PasswordEncoder encoder;
    final AuthenticationProvider provider;
    final TokenRepository tokenRepository;

    @Override
    public User saveUserDto(EntityUserRequest entityUserRequest) throws PasswordsNotMatchesException {
        if(!entityUserRequest.getPassword().equals(entityUserRequest.getConfirmPassword())){
            throw new PasswordsNotMatchesException("Password not matches with confirmation.");
        }
        entityUserRequest.setPassword(encoder.encode(entityUserRequest.getPassword()));
        User user = UserToUserDtoMapper.unMap(entityUserRequest);
        user.setRole(Role.ROLE_USER);
        user.setAccountEnabled(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public void createVerificationToken(User user, String token){
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken loadVerificationToken(String token) throws VerificationTokenNotFoundException, VerificationTokenExpiredException {
        Optional<VerificationToken> hypoteseToken = tokenRepository.findByToken(token);
        if(hypoteseToken.isEmpty()){
            throw new VerificationTokenNotFoundException("Verification token not found");
        }
        if(hypoteseToken.get().getExpiryDate().before(Date.from(Instant.now()))){
            throw new VerificationTokenExpiredException("Verification token is expired");
        }
       return hypoteseToken.get();
    }

}
