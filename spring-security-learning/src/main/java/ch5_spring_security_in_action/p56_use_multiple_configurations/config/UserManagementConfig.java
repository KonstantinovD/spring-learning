package ch5_spring_security_in_action.p56_use_multiple_configurations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

    UserDetails user =
        User.withUsername("john")
            .password("pass4")
            .authorities("read")
            .roles("user") // по итогу будет добавлен префикс "ROLE_" и добавится authority "ROLE_user"
            .build();

    userDetailsService.createUser(user);

    UserDetails user2 =
        User.withUsername("bill")
            .password("pass4_2")
            .authorities("read")
            .build();

    userDetailsService.createUser(user2);

    return userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
