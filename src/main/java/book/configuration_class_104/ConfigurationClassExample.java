package book.configuration_class_104;

import book.annotations_99.MessageProvider;
import book.annotations_99.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// xml можно заменить конфигурационным классом, не видоизменяя классы,
// nредставляющие тиnы создаваемых комnонентов Spring Beans. В таком
// случае конфигурационный класс снабжается аннотацией @Configuration и
// содержит методы, объявляемые с аннотацией @Bean и вызываемые
// непосредственно из контейнера инверсии управления для получения
// экземпляров компонентов Spring Beans.
//
// Имя компонента Spring Bean будет совпадать с именем метода,
// применяемого для его создания.

public class ConfigurationClassExample {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(
            HelloWorldConfiguration.class);

    MessageRenderer renderer =
        (MessageRenderer) applicationContext.getBean("messageRenderer");
    renderer.render();
    MessageProvider provider =
        (MessageProvider) applicationContext.getBean("messageProvider");
    System.out.println("[MAIN]: " + provider.getMessage());
  }
}
