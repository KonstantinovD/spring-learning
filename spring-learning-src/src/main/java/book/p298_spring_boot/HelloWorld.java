package book.p298_spring_boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloWorld {
  public void sayHi() {
    log.info("Hello world!");
  }
}
