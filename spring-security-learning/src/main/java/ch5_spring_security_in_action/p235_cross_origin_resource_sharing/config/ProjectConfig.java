package ch5_spring_security_in_action.p235_cross_origin_resource_sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // чтобы упростить пример
        http.authorizeRequests() // и сосредоточиться только на CORS
            .anyRequest().permitAll();
    }
}
