package by.kagan.auditlayer.config;

import lombok.RequiredArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.Optional;

@EnableJms
@Configuration
@RequiredArgsConstructor
public class ActiveMQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();

        factory.setBrokerURL("tcp://localhost:61616");
        factory.setUserName("daniel");
        factory.setPassword("16052002");
        factory.setTrustAllPackages(true);

        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory =
                new DefaultJmsListenerContainerFactory();

        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory());
        defaultJmsListenerContainerFactory.setPubSubDomain(true);

        return defaultJmsListenerContainerFactory;
    }
}
