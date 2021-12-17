package by.kagan.businesslayer.messaging;


import by.kagan.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {
    private final JmsTemplate jmsTemplate;

    public void send(AuditInfoMessage message){
        log.info("sending...");
        jmsTemplate.convertAndSend("audit-queue", message);
    }

}
