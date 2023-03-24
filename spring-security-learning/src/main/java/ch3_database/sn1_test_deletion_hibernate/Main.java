package ch3_database.sn1_test_deletion_hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Main {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-ch3_sn1");
        SpringApplication.run(Main.class, args);
    }

}
