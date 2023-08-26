package book.p104_configuration_class;

import book.p99_annotations.MessageProvider;
import book.p99_annotations.MessageRenderer;
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
