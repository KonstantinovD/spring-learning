package book.p176_beans_autolinking.withoutAutowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "book.p176_beans_autolinking.withoutAutowired")
public class AutolinkingConfiguration {
  @Bean
  public Cockpit superjetCockpit() {
    return new Cockpit("Sukhoi Superjet 100");
  }

  @Bean
  public Cockpit boeingCockpit() {
    return new Cockpit("Boeing 737");
  }

  @Bean
  public Seat seats() {
    return new Seat(100);
  }
}