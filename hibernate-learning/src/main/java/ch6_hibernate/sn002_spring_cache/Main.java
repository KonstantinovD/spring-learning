package ch6_hibernate.sn002_spring_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableCaching
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "sn002_spring_cache");
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
    }

}
