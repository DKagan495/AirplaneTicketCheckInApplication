package by.kagan.businesslayer.service;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.exception.VerificationTokenExpiredException;
import by.kagan.businesslayer.exception.VerificationTokenNotFoundException;
import by.kagan.businesslayer.repository.TokenRepository;
import by.kagan.businesslayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    final UserRepository userRepository;
    final PasswordEncoder encoder;
    final AuthenticationProvider provider;
    final TokenRepository tokenRepository;

    public void createVerificationToken(User user, String token){
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }

    public VerificationToken loadVerificationToken(String token) throws VerificationTokenNotFoundException, VerificationTokenExpiredException {
       return tokenRepository.findByToken(token)
               .orElseThrow(() -> new VerificationTokenNotFoundException("Verification token not found"));
    }
}
