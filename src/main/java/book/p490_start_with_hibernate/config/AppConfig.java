package book.p490_start_with_hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "book.p490_start_with_hibernate")
public class AppConfig {

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder.url(
        "jdbc:postgresql://127.0.0.1:5432/spring_learning_1");
    dataSourceBuilder.username("postgres");
    dataSourceBuilder.password("katukov");
    return dataSourceBuilder.build();
  }

  private Properties hibernateProperties() {
    Properties hibernateProp = new Properties();
    hibernateProp.put("hibernate.dialect",
        "org.hibernate.dialect.PostgreSQLDialect");
    hibernateProp.put("hibernate.format_sql", true);
    hibernateProp.put("hibernate.use_sql_comments", true);
    hibernateProp.put("hibernate.show_sql", true);
    hibernateProp.put("hibernate.max_fetch_depth", 3);
    hibernateProp.put ( "hibernate.jdbc.batch size", 10);
    hibernateProp.put ("hibernate.jdbc.fetch_size", 50);
    return hibernateProp;
  }

  @Bean
  public SessionFactory sessionFactory() throws IOException {
    LocalSessionFactoryBean sessionFactoryBean =
        new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource());
    sessionFactoryBean.setPackagesToScan(
        "book.p490_start_with_hibernate");
    sessionFactoryBean.setHibernateProperties(hibernateProperties());
    sessionFactoryBean.afterPropertiesSet();
    return sessionFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager()
      throws IOException {
    return new HibernateTransactionManager(sessionFactory());
  }

}
