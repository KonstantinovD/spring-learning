package book.p202_requestscopedcontroller.singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
}
