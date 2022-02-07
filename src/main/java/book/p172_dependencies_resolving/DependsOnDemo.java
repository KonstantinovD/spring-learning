package book.p172_dependencies_resolving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

@ComponentScan(basePackages = "book.p172_dependencies_resolving")
public class DependsOnDemo {
  public static void main(String[] args) {
    GenericApplicationContext ctx =
        new AnnotationConfigApplicationContext(DependsOnDemo.class);

    Singer johnMayer = ctx.getBean("johnMayer", Singer.class);
    johnMayer.sing();
    ctx.close();
  }
}
