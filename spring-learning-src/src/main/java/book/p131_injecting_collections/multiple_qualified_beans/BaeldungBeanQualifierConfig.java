package book.p131_injecting_collections.multiple_qualified_beans;

import book.p131_injecting_collections.BaeldungBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =
    "book.p131_injecting_collections.multiple_qualified_beans")
public class BaeldungBeanQualifierConfig {

  @Bean
  @Qualifier("collectionsBean")
  public BaeldungBean getElement() {
    return new BaeldungBean("FirstBean");
  }

  @Bean
  public BaeldungBean getAnotherElement() {
    return new BaeldungBean("SecondBean");
  }


  @Bean
  @Qualifier("collectionsBean")
  public BaeldungBean getOneMoreElement() {
    return new BaeldungBean("ThirdBean");
  }

  public static void main(String[] args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(
            BaeldungBeanQualifierConfig.class);
    BaeldungQualifierCollectionBean qualifierCollectionBean =
        context.getBean(BaeldungQualifierCollectionBean.class);
    qualifierCollectionBean.printBeanList();
    // [FirstBean, ThirdBean]
  }

}
