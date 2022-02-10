package book.p230_factory_beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.MessageDigest;

public class MessageDigestFactoryBean
    implements FactoryBean<MessageDigest>, InitializingBean {
  private String algorithmName = "MD5";

  private MessageDigest messageDigest = null;

  // getObject() вызывается в Spring с целью извлечь объект, созданный
  // в бине, реализующем интерфейс Factory Bean. Это конкретный объект,
  // который передается другим бинам,
  @Override
  public MessageDigest getObject() throws Exception {
    return messageDigest;
  }

  // getObjectТуре() сообщает Spring, какой именно тип объекта возвратит
  // бин-фабрика бинов. Если тип объекта заранее неизвестен, когда,
  // например, фабрика создает объекты разных типов в зависимости от
  // конфигурации (определяется лишь после инициализации этой фабрики),
  // то можно указать пустой тип null. Но если тип указан, то он может
  // быть использован в Spring для автосвязывания.
  @Override
  public Class<?> getObjectType() {
    return MessageDigest.class;
  }

  // isSingleton() сообщает Spring, что фабрика компонентов
  // Spring Beans управляет одиночным экземпляром.
  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    messageDigest = MessageDigest.getInstance(algorithmName);
  }

  public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
  }
}
