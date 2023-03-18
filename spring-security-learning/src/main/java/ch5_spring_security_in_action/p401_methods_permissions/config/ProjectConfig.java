package ch5_spring_security_in_action.p401_methods_permissions.config;

import ch5_spring_security_in_action.p401_methods_permissions.security.DocumentsPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) - можно и так, но deprecated
public class ProjectConfig { // extends GlobalMethodSecurityConfiguration -  deprecated

    @Autowired // иньекция кастомного PermissionEvaluator
    private DocumentsPermissionEvaluator evaluator;

    // реализация MethodSecurityExpressionHandler в виде bean
    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        // дефолтная реализация MethodSecurityExpressionHandler
        DefaultMethodSecurityExpressionHandler expressionHandler =
            new DefaultMethodSecurityExpressionHandler();
        // устанавливаем наш кастомный DocumentsPermissionEvaluator
        expressionHandler.setPermissionEvaluator(evaluator);
        return expressionHandler;
    }

//    // реализация MethodSecurityExpressionHandler
//    // в виде метода GlobalMethodSecurityConfiguration - deprecated
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        // дефолтная реализация MethodSecurityExpressionHandler
//        DefaultMethodSecurityExpressionHandler expressionHandler =
//            new DefaultMethodSecurityExpressionHandler();
//        // устанавливаем наш кастомный DocumentsPermissionEvaluator
//        expressionHandler.setPermissionEvaluator(evaluator);
//        return expressionHandler;
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        var service = new InMemoryUserDetailsManager();

        UserDetails u1 =
            User.withUsername("natalie")
                .password("12345")
                .roles("manager")
                .build();

        UserDetails u2 =
            User.withUsername("emma")
                .password("12345")
                .roles("admin")
                .build();

        service.createUser(u1);
        service.createUser(u2);

        return service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
