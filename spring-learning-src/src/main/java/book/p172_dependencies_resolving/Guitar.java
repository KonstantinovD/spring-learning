package book.p172_dependencies_resolving;

import org.springframework.stereotype.Component;

@Component("gopher")
public class Guitar {
  public void sing() {
    System.out.println("Cm ЕЬ Fm АЬ ВЬ");
  }
}
