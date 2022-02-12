package book.p256_application_events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Publisher implements ApplicationContextAware {
  private ApplicationContext ctx;

  @Override
  public void setApplicationContext(
      ApplicationContext applicationContext) throws BeansException {
    this.ctx = applicationContext;
  }

  public void publish(String message) {
    ctx.publishEvent(new MessageEvent(this, message));
  }

  // @Configuration - не нужно, если есть @ComponentScan
  @ComponentScan(basePackages = "book.p256_application_events")
  private static class PublisherConfig { }

  public static void main(String[] args) {
    ApplicationContext ctx =
        new AnnotationConfigApplicationContext(PublisherConfig.class);

    Publisher pub = ctx.getBean(Publisher.class);
    pub.publish("I send an SOS to the world ... ");
    pub.publish(" ... I hope that someone gets my ... ");
    pub.publish(" ... Message in а bottle");
  }

}
