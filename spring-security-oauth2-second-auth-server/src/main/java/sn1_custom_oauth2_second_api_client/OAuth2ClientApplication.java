package sn1_custom_oauth2_second_api_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OAuth2ClientApplication {
  public static void main(String[] args) {
    System.setProperty("spring.config.name", "sn1_custom_oauth2_second_api_client");
    SpringApplication.run(OAuth2ClientApplication.class, args);
  }
}
