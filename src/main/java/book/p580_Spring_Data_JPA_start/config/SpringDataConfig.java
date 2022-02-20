package book.p580_Spring_Data_JPA_start.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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


@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "book.p580_Spring_Data_JPA_start")
public class SpringDataConfig {

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
}
