package ch5_spring_security_in_action.p230_custom_csrf_token_in_db.config;

import ch5_spring_security_in_action.p230_custom_csrf_token_in_db.csrf.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public CsrfTokenRepository customTokenRepository() {
        return new CustomCsrfTokenRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(customTokenRepository());
            c.ignoringAntMatchers("/ciao");
//            c.ignoringAntMatchers("/h2-console/**"); // enable h2 console - не работает
        });

        http.authorizeRequests()
             .anyRequest().permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web // исключаем консоль h2 для случая доступа к файловой базе через браузер
            .ignoring()
            .antMatchers("/h2-console/**");
    }
}
