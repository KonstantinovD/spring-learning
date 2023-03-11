package p316_implement_auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean // стандартный бин UserDetailsService
  public UserDetailsService uds() {
    var uds = new InMemoryUserDetailsManager();
    var u = User.withUsername("john")
        .password("12345")
        .authorities("read")
        .build();
    uds.createUser(u);
    return uds;
  }

  @Bean // стандартный бин NoOpPasswordEncoder
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // We should extend the WebSecurityConfigurerAdapter
  // to access the AuthenticationManager instance
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
