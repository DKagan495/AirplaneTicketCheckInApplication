package by.kagan.auditlayer.messaging;

import by.kagan.auditlayer.service.MessageService;
import by.kagan.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@RequiredArgsConstructor
public class MessageConsumer implements MessageListener {
    private final MessageService messageService;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        AuditInfoMessage auditInfoMessage = (AuditInfoMessage) objectMessage.getObject();
        messageService.save(auditInfoMessage);
    }
}
