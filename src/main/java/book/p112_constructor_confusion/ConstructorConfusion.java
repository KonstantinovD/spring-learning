package book.p112_constructor_confusion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("constructorConfusion")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "book.p112_constructor_confusion")
public class ConstructorConfusion {
  private String someValue;

  public ConstructorConfusion(String someValue) {
    System.out.println("ConstructorConfusion(String) called");
    this.someValue = someValue;
  }

  // присвоение значения primary-полю бина
  // @Value use guide: https://www.baeldung.com/spring-value-annotation
  @Autowired
  public ConstructorConfusion(
      @Value("${constructor_confusion_112.value}") int someValue) {
    System.out.println("ConstructorConfusion(int) called");
    this.someValue = "Number: " + someValue;
  }
  // аналогичный XML выглядит так:
  /*
  <bean id="constructorConfusion"
    class="book.constructor_confusion_112.ConstructorConfusion">
	  <constructor-arg type="int">
		  <value>85</value>
	  </constructor-arg>
  </bean>
  */

  @Override
  public String toString() {
    return someValue;
  }

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(
        ConstructorConfusion.class);
    ConstructorConfusion beanConfusion =
        (ConstructorConfusion) ctx.getBean("constructorConfusion");
    System.out.println("[BEAN]: " + beanConfusion);
    ConstructorConfusion manualConfusion = new ConstructorConfusion("programmatically created class");
    System.out.println("[MANUAL]: " + manualConfusion);
  }
}
