package book.p202_requestscopedcontroller.asyncconfig;

import book.p201_bean_lifecycle_methods.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ThreadProcessor {

  @Async
  public CompletableFuture<List<Car>> getAllCars() {

    log.info(LocalTime.now() + " [Async] Start cars processing");

    List<Car> cars = new ArrayList<>();
    cars.add(new Car(140,"Volvo", true));
    cars.add(new Car(200,"Nissan", true));
    cars.add(new Car(14,"Caterpillar D9", false));

    try {
      Thread.sleep(8000);
    } catch (Exception ex) {
    }

    log.info(LocalTime.now() + " [Async] Finish cars processing");
    return CompletableFuture.completedFuture(cars);
  }
}
