package book.p202_requestscopedcontroller.asyncconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // иначе не заработает
public class AsyncConfiguration {
  // Можно обойтись и без этого (для Async)
  // И все же, мы можем сами настроить ThreadPoolTaskExecutor
  // (например,  ограничить corePoolSize, maxPoolSize, etc).
  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2); // если более 2 запросов - в очередь
    executor.setQueueCapacity(25);
    executor.setThreadNamePrefix("CarThread-");
    executor.initialize();
    return executor;
  }
}
