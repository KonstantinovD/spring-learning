package book.p202_requestscopedcontroller.singleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// test endpoint with
// curl http://localhost:8080/thread/8 & curl http://localhost:8080/thread/2 & curl http://localhost:8080/thread/0
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
  public static void main(String[] args) {
    ConfigurableApplicationContext context =
        SpringApplication.run(Application.class, args);
    ThreadPoolTaskExecutor tpte =
        context.getBean(ThreadPoolTaskExecutor.class);
    System.out.println("maxPoolSize: " + tpte.getMaxPoolSize());
    System.out.println("corePoolSize: " + tpte.getCorePoolSize());
  }
}
