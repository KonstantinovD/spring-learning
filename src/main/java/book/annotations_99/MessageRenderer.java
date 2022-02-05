package book.annotations_99;

public interface MessageRenderer {
  void render();
  void setMessageProvider(MessageProvider provider);
  MessageProvider getMessageProvider();
}
