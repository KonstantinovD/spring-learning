package ch2_plain_java.sn9_concurrent;

import java.util.concurrent.Executor;

public class Invoker implements Executor {

  @Override
  public void execute(Runnable command) {
    command.run();
  }

  public static void main(String[] args) {
    Invoker invoker = new Invoker();
    invoker.execute(() -> {
      System.out.println("Hello from Invoker");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("finish");
    });
  }
}
