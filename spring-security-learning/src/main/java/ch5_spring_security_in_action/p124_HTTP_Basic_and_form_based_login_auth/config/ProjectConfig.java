package ch5_spring_security_in_action.p124_HTTP_Basic_and_form_based_login_auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  //метод для настройки аутентификации (тип аутентификации и проч)
  // по дефолту этот метод вызывает у HttpSecurity методы httpBasic(), formLogin() и authorizeRequests()
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic(customizer -> {  // Customizer<HttpBasicConfigurer<HttpSecurity>>
      customizer.realmName("OTHER");
      customizer.authenticationEntryPoint(new CustomEntryPoint());
    });

    http.authorizeRequests().anyRequest().authenticated();
  }
}
