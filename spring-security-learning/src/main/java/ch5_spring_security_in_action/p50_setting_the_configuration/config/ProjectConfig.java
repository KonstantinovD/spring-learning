package ch5_spring_security_in_action.p50_setting_the_configuration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  // раньше мы создавали бины UserDetailsService/PasswordEncoder, а теперь регистрируем все это в методе configure
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Declares a UserDetailsService to store the users in memory
    InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
    UserDetails user =
        User.withUsername("john")
            .password("pass2")
            .authorities("read")
            .build();
    userDetailsService.createUser(user);
    // The UserDetailsService and PasswordEncoder are now set up within the configure() method.
    auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
  }

  // при этом второй метод configure(HttpSecurity http) никуда не исчезает и по-прежнему используется для настройки
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.authorizeRequests().anyRequest().authenticated();
  }
}
