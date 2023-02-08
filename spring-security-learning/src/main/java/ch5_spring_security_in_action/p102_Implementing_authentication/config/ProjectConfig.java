package ch5_spring_security_in_action.p102_Implementing_authentication.config;

import ch5_spring_security_in_action.p102_Implementing_authentication.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

//  @Autowired // autowired by CustomAuthenticationProvider
  private AuthenticationProvider authenticationProvider;
//  @Autowired
//  public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
//    this.authenticationProvider = authenticationProvider;
//  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    this.authenticationProvider = new CustomAuthenticationProvider(userDetailsService(), passwordEncoder());
    return authenticationProvider;
  }

  @Override // регаем свой CustomAuthenticationProvider
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  // также регистрируем PasswordEncoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // и UserDetailsService
  @Bean
  public UserDetailsService userDetailsService() {
    var userDetailsService = new InMemoryUserDetailsManager();

    UserDetails user =
        User.withUsername("john")
            .password("ch3_pass1")
            .authorities("read")
            .build();
    userDetailsService.createUser(user);

    return userDetailsService;
  }
}
