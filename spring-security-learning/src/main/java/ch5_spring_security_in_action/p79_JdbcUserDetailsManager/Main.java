package ch5_spring_security_in_action.p79_JdbcUserDetailsManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Tell Spring Boot to look for application-with-h2_p79.properties instead of application.properties
        System.setProperty("spring.config.name", "application-with-h2_p79");
        SpringApplication.run(Main.class, args);
    }

}
