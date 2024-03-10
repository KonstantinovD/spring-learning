package ch6_hibernate.p378_interceptors.config;

import ch6_hibernate.p378_interceptors.interceptor.AuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
@ComponentScan(basePackages = "p378_interceptors")
public class AppConfig implements HibernatePropertiesCustomizer {

    @Autowired
    private AuditLogInterceptor interceptor;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", interceptor);
    }

}
