package ch5_spring_security_in_action.p172_restrict_specific_endpoints.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetailsManager manager = new InMemoryUserDetailsManager();
    UserDetails user1 = User.withUsername("john")
        .password("1234")
        .roles("ADMIN")
        .build();
    UserDetails user2 = User.withUsername("jane")
        .password("1234")
        .roles("MANAGER")
        .build();

    manager.createUser(user1);
    manager.createUser(user2);
    return manager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.formLogin();

    http.authorizeRequests()
        .mvcMatchers("/admin").hasRole("ADMIN")
        .mvcMatchers("/manager").hasRole("MANAGER")
        .anyRequest().permitAll();
    //  .anyRequest().permitAll(); - for '/common' endpoint explicitly. But it works even without 'permitAll()'
    //  так лучше не делать - однако в этом случае Spring Security не будет делать никакой authentication
    //  и эндпоинт будет доступен
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
