package sn1_proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringAopDemoApplication implements CommandLineRunner {

  @Autowired
  Pojo pojo;

  public static void main(String[] args) {
    SpringApplication.run(SpringAopDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    pojo.test();
    System.out.println("------------------------");
    pojo.testUtil();
  }
  // test method called
  // testUtil method called
  // Execution time for Pojo.test :: 11 ms
  // ------------------------
  // testUtil method called
  // Execution time for Pojo.testUtil :: 0 ms
}
