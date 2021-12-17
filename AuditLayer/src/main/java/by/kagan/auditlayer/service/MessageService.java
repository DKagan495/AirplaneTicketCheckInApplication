package by.kagan.auditlayer.service;

import by.kagan.auditlayer.repository.MessageRepository;
import by.kagan.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public AuditInfoMessage save(AuditInfoMessage message){
        messageRepository.save(message);
        return message;
    }

    public List<AuditInfoMessage> getAll(){
        return messageRepository.findAll();
    }

}
