package ch5_spring_security_in_action.p207_static_key_authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {
    UserDetailsServiceAutoConfiguration.class,
    DataSourceAutoConfiguration.class})
public class Main {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application_p207");
        SpringApplication.run(Main.class, args);
    }

}
