package book.configuration_class_104;

import book.annotations_99.MessageProvider;

public class HelloWorldMessageProvider implements MessageProvider {
  @Override
  public String getMessage() {
    return "Second msg provider";
  }
}
