package ch5_spring_security_in_action.p56_use_multiple_configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
