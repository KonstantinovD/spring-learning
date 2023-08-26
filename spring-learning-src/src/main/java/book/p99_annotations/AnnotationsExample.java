package book.p99_annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "book.p99_annotations")
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
