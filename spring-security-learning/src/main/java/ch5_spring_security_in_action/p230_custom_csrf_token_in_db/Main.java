package ch5_spring_security_in_action.p230_custom_csrf_token_in_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-p230");
        SpringApplication.run(Main.class, args);
    }

}
