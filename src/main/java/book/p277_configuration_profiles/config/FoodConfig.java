package book.p277_configuration_profiles.config;

import book.p277_configuration_profiles.FoodProviderService;
import book.p277_configuration_profiles.highschool.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("book.p277_configuration_profiles")
public class FoodConfig {

  // used by default (when no spring.profiles.active set)
  @Bean
  @Profile("default")
  FoodProviderService foodProviderService() {
    return new FoodProviderServiceImpl();
  }

  @Bean
  @Profile("kindergarten")
  FoodProviderService foodProviderServiceTwo() {
    return new book.p277_configuration_profiles.kindergarten.FoodProviderServiceImpl();
  }
}
