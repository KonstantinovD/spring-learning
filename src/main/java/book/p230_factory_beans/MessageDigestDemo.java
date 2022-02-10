package book.p230_factory_beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class MessageDigestDemo {

  @Configuration
  static class MessageDigestConfig {
    @Bean
    public MessageDigestFactoryBean factoryBeanOne() {
      return new MessageDigestFactoryBean();
    }

    @Bean
    public MessageDigestFactoryBean factoryBeanTwo() {
      MessageDigestFactoryBean factoryBean =
          new MessageDigestFactoryBean();
      factoryBean.setAlgorithmName("SHA1");
      return factoryBean;
    }

    // Если конфигурирование осуществляется на языке Java, то,
    // поскольку компилятор накладывает ограничение на установку
    // надлежащего типа данных в свойстве, метод getObject()
    // необходимо вызывать вручную (в отличие от XML).
    @Bean
    public MessageDigester messageDigester() throws Exception {
      MessageDigester messageDigester = new MessageDigester();
      messageDigester.setDigest1(factoryBeanOne().getObject());
      messageDigester.setDigest2(factoryBeanTwo().getObject());
      return messageDigester;
    }

    public static void main(String[] args) {
      GenericApplicationContext ctx =
          new AnnotationConfigApplicationContext(
              MessageDigestConfig.class);
      MessageDigester digester = ctx.getBean(MessageDigester.class);
      digester.digest("Hello world!");
      ctx.close();
    }
  }
}
