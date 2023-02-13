package ch5_spring_security_in_action.p153_Authorization_RestrictingAccess.config;

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
    UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

    UserDetails u1 = User.withUsername("john")
        .password("1234")
        .authorities("READ", "WRITE")
        .build();
    UserDetails u2 = User.withUsername("jane")
        .password("1234")
        .authorities("READ")
        .build();
    UserDetails u3 = User.withUsername("notifier")
        .password("1234")
        .authorities("NOTIFY")
        .build();

    userDetailsManager.createUser(u1);
    userDetailsManager.createUser(u2);
    userDetailsManager.createUser(u3);
    return userDetailsManager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.formLogin();

    http.authorizeRequests()
        .antMatchers("/info").permitAll()
        .antMatchers("/aboutYou").authenticated() // any authenticated user can access
        .antMatchers("/read").hasAuthority("READ")
        // both authorities READ & WRITE should be present
        .antMatchers("/write").access("hasAuthority('READ') and hasAuthority('WRITE')")
        .antMatchers("/notify").hasAnyAuthority("WRITE", "NOTIFY")
        .antMatchers("/dangerous").denyAll();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
