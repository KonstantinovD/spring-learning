package book.p298_spring_boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class Application {
  public static void main(String[] args) throws Exception{
    ConfigurableApplicationContext context =
        SpringApplication.run(Application.class, args);

    log.info("The beans you were looking for:");
    Arrays.stream(context.getBeanDefinitionNames())
        .forEach(System.out::println);

    HelloWorld hw = context.getBean(HelloWorld.class);
    hw.sayHi();
  }
}
