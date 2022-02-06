package book.p104_configuration_class;

import book.p99_annotations.MessageProvider;

public class HelloWorldMessageProvider implements MessageProvider {
  @Override
  public String getMessage() {
    return "Second msg provider";
  }
}
