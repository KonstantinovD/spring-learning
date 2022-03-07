package book.p131_injecting_collections.multiple_beans;

import book.p131_injecting_collections.BaeldungBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "book.p131_injecting_collections.multiple_beans")
public class BaeldungBeanConfig {
  @Bean
  public BaeldungBean getElement() {
    return new BaeldungBean("FirstBean");
  }

  @Bean
  public BaeldungBean getAnotherElement() {
    return new BaeldungBean("SecondBean");
  }


  @Bean
  public BaeldungBean getOneMoreElement() {
    return new BaeldungBean("ThirdBean");
  }

  public static void main(String[] args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(
            BaeldungBeanConfig.class);
    BaeldungCollectionBean collectionBean =
        context.getBean(BaeldungCollectionBean.class);
    collectionBean.printBeanList();
    // [FirstBean, SecondBean, ThirdBean]
  }

}
