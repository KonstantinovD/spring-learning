package ch2_plain_java.sn4_threads_wait_notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyStoreDemo {

  public static void main(String[] args) {
    Store store=new Store();
    Producer producer = new Producer(store);
    Consumer consumer = new Consumer(store);
    new Thread(producer).start();
    new Thread(consumer).start();
  }

}

// Класс Магазин, хранящий произведенные товары
class Store{
  private int product=0;

  public Store() { }

  public synchronized void get() {
    while (product < 1) {
      try {
        System.out.println(
            "--- Покупатель не может ничего купить:"
                + " нет товаров");
        wait();
      }
      catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }

    int operate = operateProduct(-1);
    System.out.println("Покупатель купил 1 товар." +
        " Товаров на складе: " + operate);
    notifyAll();
  }
  public synchronized void put() {
    while (product >= 3) {
      try {
        System.out.println(
            "--- Производитель не может доставить товар:"
                + " склад полностью заполнен (максимум = 3)");
        wait();
      }
      catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }
    int operate = operateProduct(1);
    System.out.println("Производитель добавил 1 товар." +
        " Товаров на складе: " + operate);
    notifyAll();
  }

  private synchronized int operateProduct(int delta) {
    product += delta;
    return product;
  }
}

// класс Производитель
class Producer implements Runnable{

  Store store;
  Producer(Store store){
    this.store=store;
  }
  public void run(){
    for (int i = 1; i < 6; i++) {
      new Thread(() -> store.put()).start();
    }
  }
}
// Класс Потребитель
class Consumer implements Runnable{

  Store store;
  Consumer(Store store){
    this.store=store;
  }
  public void run(){
    for (int i = 1; i < 6; i++) {
      new Thread(() -> store.get()).start();
    }
  }
}
