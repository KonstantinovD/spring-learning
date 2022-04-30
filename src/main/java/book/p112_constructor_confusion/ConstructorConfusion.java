package book.p112_constructor_confusion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


// Иногда у Spring нет возможности выяснить, какой именно конструктор требуется
// использовать для внедрения зависимостей. Как правило, это происходит в том случае,
// если имеются два конструктора с одним и тем же количеством аргументов и
// одинаково обозначенными их типами.
// Например, у нас тут два конструктора: string/int параметры. Если ни один из них не
// делать дефолтным, то получим ошибку:
//
// Failed to instantiate [book.p112_constructor_confusion.ConstructorConfusion]:
//   No default constructor found;
//
// Поэтому на один из них надо повесить аннотацию @Autowired
@Service("constructorConfusion")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "book.p112_constructor_confusion")
public class ConstructorConfusion {
  private String someValue;

  // присвоение значения primary-полю бина
  // @Value use guide: https://www.baeldung.com/spring-value-annotation
  public ConstructorConfusion(@Value("${constructor_confusion_112.value}") String someValue) {
    System.out.println("ConstructorConfusion(String) called");
    this.someValue = someValue;
  }

  @Autowired
  private Environment env;

  public String getProp() {
    return env.containsProperty("constructor_confusion_112.kvak") + " " +
        env.containsProperty("constructor_confusion_112.value");
  }

//  @Value("${constructor_confusion_112.kvak}")
//  private boolean integrated;

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

    System.out.println(beanConfusion.getProp());
  }
}
