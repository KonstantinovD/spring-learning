package book.p202_requestscopedcontroller.singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@Slf4j
@RestController
public class MultithreadController {

  @Autowired
  ThreadProcessor threadProcessor;

  // test endpoint with
  // curl http://localhost:8080/thread & curl http://localhost:8080/thread & curl http://localhost:8080/thread
  @GetMapping("/thread")
  public String index() {
    log.info(LocalTime.now() + "Controller in");
    threadProcessor.process();
    log.info(LocalTime.now() + "Controller out");
    return "thread processed";
  }
}
