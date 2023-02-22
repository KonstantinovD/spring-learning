package ch5_spring_security_in_action.p210_OncePerRequestFilter_impl.config;

import ch5_spring_security_in_action.p210_OncePerRequestFilter_impl.filters.AuthenticationLoggingFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(  // метод для добавления фильтра после другого
                new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
            .authorizeRequests()
            .anyRequest().permitAll(); // разрешаем все запросы для простоты
    }
}
