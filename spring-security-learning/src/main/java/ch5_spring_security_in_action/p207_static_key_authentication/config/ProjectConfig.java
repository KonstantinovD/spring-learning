package ch5_spring_security_in_action.p207_static_key_authentication.config;

import ch5_spring_security_in_action.p207_static_key_authentication.filters.StaticKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private StaticKeyAuthenticationFilter filter; // inject custom filter

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // don't call http.httpBasic()
    // to prevent adding BasicAuthenticationFilter to chain
    http.addFilterAt(filter, BasicAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest().permitAll();
  }
}
