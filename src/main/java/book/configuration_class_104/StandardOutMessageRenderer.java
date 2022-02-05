package book.configuration_class_104;

import book.annotations_99.MessageProvider;
import book.annotations_99.MessageRenderer;

public class StandardOutMessageRenderer implements MessageRenderer {

  private MessageProvider messageProvider;

  @Override
  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException("You must set the "
          + "property messageProvider of class:"
          + StandardOutMessageRenderer.class.getName());
    }
    System.out.println("[SECOND MSG RENDER]: "
        + messageProvider.getMessage());
  }

  @Override // конфигурации нет
  public void setMessageProvider(MessageProvider provider) {
    this.messageProvider = provider;
  }

  @Override
  public MessageProvider getMessageProvider() {
    return messageProvider;
  }
}
