package ch1_spring.trim_json_strings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class TrimStrsFromJsonApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrimStrsFromJsonApplication.class, args);
    }
}
