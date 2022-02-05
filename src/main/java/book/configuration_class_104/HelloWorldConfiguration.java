package book.configuration_class_104;

import book.annotations_99.MessageProvider;
import book.annotations_99.MessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
  @Bean
  public MessageProvider messageProvider() {
    return new HelloWorldMessageProvider();
  }

  @Bean
  public MessageRenderer messageRenderer() {
    MessageRenderer renderer = new StandardOutMessageRenderer();
    renderer.setMessageProvider(messageProvider());
    return renderer;
  }
}
