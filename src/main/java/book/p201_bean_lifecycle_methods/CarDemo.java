package book.p201_bean_lifecycle_methods;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class CarDemo {

  @Autowired
  private List<Car> carList;

  @Configuration
  @ComponentScan(basePackages = "book.p201_bean_lifecycle_methods")
  static class CarConfig {
    @Bean
    public Car carOne() {
      return new Car(90, "ZIL-130", true);
    }


    @Bean(initMethod = "init", destroyMethod = "destroyBean")
    public Car carThree() {
      // uses technical propane as fuel
      return new Car(80,
          "ZIL-138A", false);
    }
  }

  public static void main(String[] args) {
    GenericApplicationContext ctx =
        new AnnotationConfigApplicationContext(
            CarConfig.class);
    CarDemo carDemo = ctx.getBean(CarDemo.class);
    System.out.println();
    for (Car c : carDemo.getCarList()) {
      System.out.println(c);
    }
    System.out.println();

    ctx.registerShutdownHook();
  }

}
