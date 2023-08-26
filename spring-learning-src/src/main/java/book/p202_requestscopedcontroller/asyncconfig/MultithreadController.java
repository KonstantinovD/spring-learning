package book.p202_requestscopedcontroller.asyncconfig;

import book.p201_bean_lifecycle_methods.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;


@Slf4j
@RestController
public class MultithreadController {

  @Autowired
  ThreadProcessor threadProcessor;

  @RequestMapping(method = RequestMethod.GET,
      produces={MediaType.APPLICATION_JSON_VALUE}, value = "cars")
  public String getAllCars() {
    log.info(LocalTime.now() + "getAllCars method started");
    threadProcessor.getAllCars();
    log.info(LocalTime.now() + "getAllCars method finished");
    return "{\"s\":\"hello\"}";
  }

  private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
    log.error(String.format("Failed to read records: %s", throwable));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  };
}
