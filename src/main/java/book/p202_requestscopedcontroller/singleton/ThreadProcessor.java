package book.p202_requestscopedcontroller.singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ThreadProcessor {

  public void process() {
    log.info(LocalTime.now() + "Start thread processing");
    try {
      Thread.sleep(8000);
    } catch (Exception ex) {
    }
    log.info(LocalTime.now() + "Finish thread processing");
  }
}
