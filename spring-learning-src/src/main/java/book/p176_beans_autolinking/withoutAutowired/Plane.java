package book.p176_beans_autolinking.withoutAutowired;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component("myPlane")
@Getter
//@AllArgsConstructor - как вариант
public class Plane {

  private Seat seat;
  private Cockpit superjetCockpit;

  //в случае объявления сеттеров внедрение зависимостей не происходит
  public Plane(Seat seat, Cockpit superjetCockpit) {
    this.seat = seat;
    this.superjetCockpit = superjetCockpit;
  }

}
