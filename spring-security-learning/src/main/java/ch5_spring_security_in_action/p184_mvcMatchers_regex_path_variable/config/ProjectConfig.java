package ch5_spring_security_in_action.p184_mvcMatchers_regex_path_variable.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
            // Обычно пишут вот так - используют с regex только path variables:
            .mvcMatchers( "/product/{code:^[0-9]*$}").permitAll()
            // Но можно сделать и так - с regex матчить и обычные эндпоинты. Это тоже может быть ограничением
            // поскольку, например, эндпоинт '/regex/Capital/{code}' в указанный ниже regex не матчится.
            // В целом, это можно использовать для api-gateway сервисов:
            .mvcMatchers( "/regex/{:^[a-z]*$}/{code:^[0-9]*$}").permitAll()
            .anyRequest().denyAll();
    }
}
