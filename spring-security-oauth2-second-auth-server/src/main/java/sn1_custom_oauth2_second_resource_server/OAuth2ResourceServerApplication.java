package sn1_custom_oauth2_second_resource_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OAuth2ResourceServerApplication {

  public static void main(String[] args) {
    System.setProperty("spring.config.name", "sn1_custom_oauth2_second_resource_server");
    SpringApplication.run(OAuth2ResourceServerApplication.class, args);
  }

}
