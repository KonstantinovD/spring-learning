package book.p141_di_via_lookup_method;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {

  private Singer mySinger;

  // В этом случае prototype будет создан один раз и никогда не обновляться,
  // т е весь свой жизненный цикл StandardLookupDemoBean будет юзать
  // один и тот же экземпляр Singer
  @Resource(name = "singer")
  public void setMySinger(Singer mySinger) {
    this.mySinger = mySinger;
  }

  @Override
  public Singer getMySinger() {
    return mySinger;
  }

  // используется сохраненный экземпляр класса Singer.
  @Override
  public void doSomething() {
    mySinger.sing();
  }
}
