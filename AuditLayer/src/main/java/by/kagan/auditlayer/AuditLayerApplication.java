package by.kagan.auditlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class AuditLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditLayerApplication.class, args);
    }

}
