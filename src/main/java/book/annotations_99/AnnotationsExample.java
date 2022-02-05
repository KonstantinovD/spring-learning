package book.annotations_99;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "book.annotations_99")
public class AnnotationsExample {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(AnnotationsExample.class);

    MessageRenderer renderer =
        (MessageRenderer) applicationContext.getBean("renderer");
    renderer.render();
    MessageProvider provider =
        (MessageProvider) applicationContext.getBean("provider");
    System.out.println("[MAIN]: " + provider.getMessage());
  }
}
