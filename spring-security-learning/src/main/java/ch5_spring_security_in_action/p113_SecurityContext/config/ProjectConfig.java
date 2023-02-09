package ch5_spring_security_in_action.p113_SecurityContext.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  // Интерфейс InitializingBean позволяет указывать обратный вызов при
  // инициализации один раз для всех экземпляров класса компонента Bean
  @Bean
  public InitializingBean initializingBean() {
    // если пропустить это, то при вызов эндпоинта "/bye" получим NPE
    return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
  }

}
