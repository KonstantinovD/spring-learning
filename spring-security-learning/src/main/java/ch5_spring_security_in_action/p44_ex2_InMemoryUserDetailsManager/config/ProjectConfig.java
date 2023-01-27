package ch5_spring_security_in_action.p44_ex2_InMemoryUserDetailsManager.config;

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

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  // создаем двух пользователей
  @Bean
  public UserDetailsService userDetailsService() {
    var userDetailsService = new InMemoryUserDetailsManager();

    UserDetails user =
        User.withUsername("john")
            .password("pass1")
            .authorities("read")
            .build();

    UserDetails user2 =
        User.withUsername("bill")
            .password("pass1_2")
            .authorities("read")
            .build();

    userDetailsService.createUser(user);
    userDetailsService.createUser(user2);

    return userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.authorizeRequests().anyRequest().authenticated();
    // если закомментить строчку выше и раскомментить эту строчку, то получится что мы уберем все секьюрити с всех эндпоинтов
//    http.authorizeRequests().anyRequest().permitAll();
  }
}
