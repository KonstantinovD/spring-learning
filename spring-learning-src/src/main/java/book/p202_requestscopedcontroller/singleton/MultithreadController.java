package book.p202_requestscopedcontroller.singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.OperationNotSupportedException;
import java.time.LocalTime;

@Slf4j
@RestController
public class MultithreadController {

  @Autowired
  ThreadProcessor threadProcessor;

  // test endpoint with
  // curl http://localhost:8080/thread & curl http://localhost:8080/thread & curl http://localhost:8080/thread
  @GetMapping("/thread/{seconds}")
  public String index(@PathVariable int seconds) {
    log.info(LocalTime.now() + "Controller in");
    threadProcessor.process(seconds);
    log.info(LocalTime.now() + "Controller out");
    return "thread processed";
  }

  @GetMapping("/futures/{s1}/{s2}/{s3}/{timeout}")
  public String futures(@PathVariable int s1, @PathVariable int s2, @PathVariable int s3, @PathVariable int timeout) {
    log.info(LocalTime.now() + " Futures controller in");
    String res = threadProcessor.processFutures(s1, s2, s3, timeout);
    log.info(LocalTime.now() + " Futures controller out");
    return res;
  }

  @GetMapping("/completionService/{s1}/{s2}/{s3}/{timeout}")
  public String tryExecutorCompletionService(
      @PathVariable int s1, @PathVariable int s2, @PathVariable int s3, @PathVariable int timeout) throws OperationNotSupportedException {
    throw new OperationNotSupportedException("ExecutorCompletionService cannot process timeouts in a proper way");
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorCompletionService.html
    // https://java2blog.com/java-executorcompletionservice-example/
  }
}
