package ch6_hibernate.sn001_inner_transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TestNestedTransactions {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "sn001-application");
        SpringApplication.run(TestNestedTransactions.class, args);
    }
}
