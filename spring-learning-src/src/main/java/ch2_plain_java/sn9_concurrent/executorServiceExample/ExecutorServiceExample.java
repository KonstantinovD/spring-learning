package ch2_plain_java.sn9_concurrent.executorServiceExample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExecutorServiceExample {
  ExecutorService executor;

  public ExecutorServiceExample() {
    executor = Executors.newFixedThreadPool(5);
  }

  public void executeAndHandleException(Runnable task) {
    Future future = executor.submit(task);
    try {
      future.get();
    } catch (Exception ex) {
      String msg = ex.getMessage();
      if (ex.getCause() != null) {

        if (ex.getCause() instanceof IllegalArgumentException) {
          throw (RuntimeException) ex.getCause();
        }

        msg = ex.getCause().getMessage();
        log.error(msg, ex.getCause());
      }
      throw new ExecutorServiceException(msg);
    }
  }

  public void waitForPoolFinish() {
    try {
      executor.shutdown();
      while (!executor.awaitTermination(
          100, TimeUnit.MILLISECONDS)) {
        log.info("Waiting for ThreadPool completion");
      }
    } catch (InterruptedException iex) {
      log.error("Executor service was interrupted");
      Thread.currentThread().interrupt();
      throw new ExecutorServiceException(
          "Executor service was interrupted: " + iex.getMessage());
    }
  }

  public static void main(String[] args) {

    try {
      ExecutorServiceExample exse = new ExecutorServiceExample();

      for (int i = 1; i < 6; i++) {
        exse.executeAndHandleException(buildSimpleWaitingTask(i));
      }
      exse.waitForPoolFinish();
      log.info("All the threads in pool are finished - 1");
      log.info("------------------------------------------");

      ExecutorServiceExample exse2 = new ExecutorServiceExample();
      for (int i = 1; i < 6; i++) {
        if (i == 3) {
          exse2.executeAndHandleException(buildExceptionTask(i));
        } else {
          exse2.executeAndHandleException(buildSimpleWaitingTask(i));
        }
      }
      exse2.waitForPoolFinish();
      log.info("All the threads in pool are finished - 2");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(-113);
    }
  }

  public static Runnable buildSimpleWaitingTask(int number) {
    return () -> {
      log.info(String.format(
          "Thread %d started; sleep %d ms", number, number*30));
      try {
        Thread.sleep(number * 30L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info(String.format("Thread %d finished", number));
    };
  }

  public static Runnable buildExceptionTask(int number) {
    return () -> {
      log.info(String.format(
          "Exception thread %d started; sleep %d ms", number, number*30));
      try {
        Thread.sleep(30L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      throw new IllegalArgumentException(
          "test message with additional DATA");
    };
  }
}
