package book.p131_injecting_collections.autowire_collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CollectionConfig {

  @Bean
  public CollectionsBean getCollectionsBean() {
    return new CollectionsBean();
  }

  @Bean
  public List<String> nameList() {
    return Arrays.asList("John", "Adam", "Harry");
  }

  public static void main(String[] args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(
            CollectionConfig.class);
    CollectionsBean collectionsBean =
        context.getBean(CollectionsBean.class);
    collectionsBean.printNameList(); // [John, Adam, Harry]
  }
}
