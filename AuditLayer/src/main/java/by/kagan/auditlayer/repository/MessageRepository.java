package by.kagan.auditlayer.repository;

import by.kagan.message.AuditInfoMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<AuditInfoMessage, String> {
}
