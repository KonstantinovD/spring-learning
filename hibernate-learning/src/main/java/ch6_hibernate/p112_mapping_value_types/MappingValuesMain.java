package ch6_hibernate.p112_mapping_value_types;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class MappingValuesMain {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "p112_mapping_value_types");
        SpringApplication.run(MappingValuesMain.class, args);
    }

}