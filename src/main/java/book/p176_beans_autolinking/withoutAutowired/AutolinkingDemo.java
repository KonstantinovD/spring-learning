package book.p176_beans_autolinking.withoutAutowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class AutolinkingDemo {

  public static void main(String[] args) {
    GenericApplicationContext ctx =
        new AnnotationConfigApplicationContext(AutolinkingConfiguration.class);

    Plane plane = ctx.getBean("myPlane", Plane.class);

    System.out.printf("\n\nPlane, seats=%d, version=%s\n\n",
        plane.getSeat().getAmount(), plane.getSuperjetCockpit().getVersion());

    ctx.close();
  }

}
