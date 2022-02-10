package book.p230_factory_beans;

import lombok.Setter;

import java.security.MessageDigest;

@Setter
public class MessageDigester {
  // пример простого бина, обслуживающего два экземпляра типа
  // MessageDigest и отображающего свертки сообщения,
  // передаваемого методу digest()

  private MessageDigest digest1;
  private MessageDigest digest2;

  public void digest(String msg) {
    System.out.println("Using digestl");
    digest(msg, digest1);
    System.out.println("Using digest2");
    digest(msg, digest2);
  }

  private void digest(String msg, MessageDigest digest) {
    System.out.println("Using algorithm: " + digest.getAlgorithm());
    digest.reset();
    byte[] bytes = msg.getBytes();
    byte[] out = digest.digest(bytes);
    System.out.println(out);
  }

}
