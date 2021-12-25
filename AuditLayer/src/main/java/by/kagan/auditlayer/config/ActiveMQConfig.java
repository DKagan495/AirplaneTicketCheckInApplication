package by.kagan.auditlayer.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Getter
@Configuration
@RequiredArgsConstructor
public class ActiveMQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        var factory = new ActiveMQConnectionFactory();

        factory.setBrokerURL("tcp://localhost:61616");
        factory.setTrustAllPackages(true);

        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(){
       var defaultJmsListenerContainerFactory =
                new DefaultJmsListenerContainerFactory();

       defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory());
       defaultJmsListenerContainerFactory.setPubSubDomain(true);

       return defaultJmsListenerContainerFactory;
    }
}
