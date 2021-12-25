package by.kagan.auditlayer.messaging;

import by.kagan.auditlayer.service.MessageService;
import by.kagan.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer implements MessageListener {
    private final MessageService messageService;

    @Override
    @JmsListener(destination = "audit-queue")
    public void onMessage(Message message) {
        try {
            log.info("consuming...");
            ObjectMessage objectMessage = (ObjectMessage) message;
            AuditInfoMessage auditInfoMessage = (AuditInfoMessage) objectMessage.getObject();
            messageService.save(auditInfoMessage);
        }
        catch (Exception e){
            log.error("Receiving exception: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
