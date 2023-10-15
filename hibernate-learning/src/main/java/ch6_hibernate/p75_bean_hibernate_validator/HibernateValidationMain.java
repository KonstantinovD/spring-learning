package ch6_hibernate.p75_bean_hibernate_validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class HibernateValidationMain {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "p75_bean_hibernate_validator");
        SpringApplication.run(HibernateValidationMain.class, args);
    }
}
