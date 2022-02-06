package book.p131_injecting_collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class SetConfig {

  @Bean
  public SetBean getSetBean() {
    return new SetBean(new HashSet<>(
        Arrays.asList("John", "Adam", "Harry")));
  }

  public static void main(String[] args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(
            SetConfig.class);
    SetBean setBean = context.getBean(SetBean.class);
    setBean.printNameSet(); // [John, Adam, Harry]
  }
}
