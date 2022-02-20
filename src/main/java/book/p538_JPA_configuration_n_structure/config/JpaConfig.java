package book.p538_JPA_configuration_n_structure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//compare it with Hibernate config:
//       p490_start_with_hibernate.config.AppConfig.java
@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( // for book.p580_Spring_Data_JPA_start
    basePackages = "book.p580_Spring_Data_JPA_start")
@ComponentScan(basePackages = "book.p538_JPA_configuration_n_structure")
// for book.p580_Spring_Data_JPA_start
@ComponentScan(basePackages = "book.p580_Spring_Data_JPA_start")
public class JpaConfig {

  @Bean
  public DataSource dataSource() {
    try {
      DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
      dataSourceBuilder.driverClassName("org.postgresql.Driver");
      dataSourceBuilder.url(
          "jdbc:postgresql://127.0.0.1:5432/spring_learning_1");
      dataSourceBuilder.username("postgres");
      dataSourceBuilder.password("katukov");
      return dataSourceBuilder.build();
    } catch (Exception ex) {
      log.error("Embedded DataSource bean cannot bе created!", ex);
      return null;
    }
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory());
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean =
        new LocalContainerEntityManagerFactoryBean();
    factoryBean.setPackagesToScan(
        "book.p538_JPA_configuration_n_structure");
    factoryBean.setDataSource(dataSource());
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
    factoryBean.setJpaProperties(hibernateProperties());
    factoryBean.afterPropertiesSet();
    return factoryBean.getNativeEntityManagerFactory();
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
}
