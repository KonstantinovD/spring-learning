package book.p172_dependencies_resolving;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("johnMayer")
@DependsOn("gopher")
public class Singer implements ApplicationContextAware {

  private ApplicationContext ctx;
  private Guitar guitar;

  @Override
  public void setApplicationContext(
      ApplicationContext applicationContext) throws BeansException {
    this.ctx = applicationContext;
  }

  public void sing() {
    guitar = ctx.getBean("gopher", Guitar.class);
    guitar.sing();
  }
}
