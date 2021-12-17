package by.kagan.auditlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableJms
public class AuditLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditLayerApplication.class, args);
    }

}
