package book.p202_requestscopedcontroller.singleton;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ThreadProcessor {

  public void process(int seconds) {
    log.info(LocalTime.now() + "Start thread processing, seconds = " + seconds);

    if (seconds > 0) {
      try {
        Thread.sleep(seconds * 1000);
      } catch (Exception ex) {
      }
    } else {
      log.info(LocalTime.now() + "Immediately execution");
    }
    log.info(LocalTime.now() + "Finish thread processing");
  }

  public String processFutures(int s1, int s2, int s3, int timeout) {
    log.info(LocalTime.now() + " Start futures processing, timeout = " + timeout);

    List<CompletableFuture<String>> futures = new ArrayList<>();
    futures.add(
        CompletableFuture.supplyAsync(() -> executeTask(1, s1)));
    futures.add(
        CompletableFuture.supplyAsync(() -> executeTask(2, s2)));
    futures.add(
        CompletableFuture.supplyAsync(() -> executeTask(3, s3)));
    futures.forEach(f ->
        f.exceptionally(t -> {
          log.error("Future thread fails with an exception: " + t);
          futures.forEach(runningFuture -> runningFuture.completeExceptionally(t));
          return null;
        })
    );

    CompletableFuture<Void> combinedFuture
        = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

    try {
      combinedFuture.get(timeout, TimeUnit.SECONDS);
    } catch (TimeoutException tex) {
      throw new RuntimeException("Timeout is reached");
    } catch (Exception ex) {
      String message = "unhandled exception";
      if (StringUtils.isNoneEmpty(ex.getMessage())) {
        message = ex.getMessage();
      }
      throw new IllegalArgumentException(message);
    }

    StringBuilder res = new StringBuilder();
    for (CompletableFuture<String> f : futures) {
      String r;
      try {
        r = f.get();
      } catch (Exception ex) {
        throw new IllegalArgumentException("cannot get result from future");
      }
      if (r != null) {
        res.append(r);
        res.append(" ");
      }
    }

    log.info(LocalTime.now() + " Finish futures processing");
    return res.toString();
  }

  private String executeTask(int number, int seconds) {
    log.info(LocalTime.now() + String.format(" Start thread %d processing, seconds = %d", number, seconds));
    if (seconds < 0) {
      throw new RuntimeException(String.format("thread %d exception : seconds cannot be negative", number));
    }
    try {
      Thread.sleep(seconds * 1000);
    } catch (Exception ex) {
      throw new RuntimeException(String.format("thread %d exception", number));
    }
    log.info(LocalTime.now() + String.format(" Finish thread %d processing", number));
    return "Thread 2";
  }
}
