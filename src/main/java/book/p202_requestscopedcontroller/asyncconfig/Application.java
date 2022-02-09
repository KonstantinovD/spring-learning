package book.p202_requestscopedcontroller.asyncconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// test async endpoint with
// curl http://localhost:8080/cars & curl http://localhost:8080/cars & curl http://localhost:8080/cars
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
