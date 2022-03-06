package ch2_plain_java.sn9_concurrent;

import java.util.concurrent.*;

public class ScheduledExecutorServiceExample {

  public static void main(String[] args) {
    ScheduledExecutorService executorService
        = Executors.newSingleThreadScheduledExecutor();

    Future<String> future = executorService.schedule(() -> {
      // ...
      return "Hello world";
    }, 2, TimeUnit.SECONDS);

    executorService.shutdown();

    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("End of Main");
  }

}
