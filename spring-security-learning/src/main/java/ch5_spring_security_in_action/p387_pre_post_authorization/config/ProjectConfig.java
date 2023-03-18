package ch5_spring_security_in_action.p387_pre_post_authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
// Enables global method security for pre-/postauthorization
// '@EnableGlobalMethodSecurit can be used - but it's better to use '@EnableMethodSecurity'
// '@EnableGlobalMethodSecurity' deprecated since Spring Security 5.8.0

// @EnableGlobalMethodSecurity
@EnableMethodSecurity
public class ProjectConfig {

  @Bean // Adds a UserDetailsService with two users for testing
  public UserDetailsService userDetailsService() {
    var service = new InMemoryUserDetailsManager();

    var u1 = User.withUsername("natalie")
        .password("12345")
        .authorities("read")
        .build();

    var u2 = User.withUsername("emma")
        .password("12345")
        .authorities("write")
        .build();

    service.createUser(u1);
    service.createUser(u2);

    return service;
  }

  @Bean // Adds a PasswordEncoder for users
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

}
