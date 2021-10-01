package by.kagan.businesslayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BusinessLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessLayerApplication.class, args);
    }

}
