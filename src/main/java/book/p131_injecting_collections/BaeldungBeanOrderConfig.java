package book.p131_injecting_collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan(basePackages = "book.p131_injecting_collections")
public class BaeldungBeanOrderConfig {

  @Bean
  // We can specify the order of the beans while injecting into
  // the collection using the @Order annotation
  // For that purpose, we use the @Order annotation and specify the index:
  @Order(2)
  public BaeldungBean getElement() {
    return new BaeldungBean("FirstBean");
  }

  @Bean
  @Order(3)
  public BaeldungBean getAnotherElement() {
    return new BaeldungBean("SecondBean");
  }


  @Bean
  @Order(1)
  public BaeldungBean getOneMoreElement() {
    return new BaeldungBean("ThirdBean");
  }

  public static void main(String[] args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(
            BaeldungBeanOrderConfig.class);
    BaeldungCollectionBean collectionBean =
        context.getBean(BaeldungCollectionBean.class);
    collectionBean.printBeanList();
    // [ThirdBean, FirstBean, SecondBean]
  }
}
