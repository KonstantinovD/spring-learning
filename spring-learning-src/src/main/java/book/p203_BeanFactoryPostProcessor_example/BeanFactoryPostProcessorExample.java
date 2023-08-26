package book.p203_BeanFactoryPostProcessor_example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanFactoryPostProcessorExample {

  public static void main (String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(MyConfig.class);
    MyBean bean = context.getBean(MyBean.class);
    bean.doSomething();
  }

  @Configuration
  public static class MyConfig {
    @Bean
    MyConfigBean myConfigBean () {
      return new MyConfigBean();
    }
  }

  private static class MyConfigBean implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory (
        ConfigurableListableBeanFactory beanFactory)
        throws BeansException {

      GenericBeanDefinition bd = new GenericBeanDefinition();
      bd.setBeanClass(MyBean.class);
      bd.getPropertyValues().add("strProp", "my string property");

      ((DefaultListableBeanFactory) beanFactory)
          .registerBeanDefinition("myBeanName", bd);
    }
  }

  private static class MyBean {
    private String strProp;

    public void setStrProp(String strProp) {
      this.strProp = strProp;
    }

    public void doSomething () {
      System.out.println("from MyBean:  " + strProp);
    }
  }
}