package by.kagan.businesslayer.repository;

import by.kagan.businesslayer.auth.token.verification.VerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
