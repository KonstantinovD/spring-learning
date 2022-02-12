package book.p256_application_events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MessageEventListener
    implements ApplicationListener<MessageEvent> {

  // Метод вызывается, когда наступает событие. Класс
  // MessageEventListener реагирует только на события типа
  // MessageEvent (или его подтипов), т.к. в нем реализуется
  // строго типизированный интерфейс ApplicationListener
  @Override
  public void onApplicationEvent(MessageEvent event) {
    System.out.println("Received: " + event.getMessage());
  }
}
