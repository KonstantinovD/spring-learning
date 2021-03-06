package book.p141_di_via_lookup_method;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("specificLookupBean")
public class SpecificLookupDemoBean implements DemoBean {

  // В этом случае prototype будет пересоздаваться каждый раз при запросе Singer
  // через метод getMySinger
  @Lookup("singer")
  public Singer getMySinger() {
    return null; // переопределяется автоматически
  }

  @Override
  public void doSomething() {
    getMySinger().sing();
  }
}
