package ch6_hibernate.p65_presisted_object_and_hibernate_properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "p65_presisted_object_and_hibernate_properties");
        SpringApplication.run(Main.class, args);
    }

}