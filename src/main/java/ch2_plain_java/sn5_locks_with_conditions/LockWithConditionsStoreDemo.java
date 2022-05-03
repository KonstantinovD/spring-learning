package ch2_plain_java.sn5_locks_with_conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockWithConditionsStoreDemo {

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
  private final Lock locker;
  private final Condition productState;
  private final Condition productState2;

  public Store(){
    locker = new ReentrantLock();
    productState = locker.newCondition();
    productState2 = locker.newCondition();
  }

  public void get() {
    try {
      locker.lock(); // potentially deadlock
      while (product < 1) {
        System.out.println(
            "--- Покупатель не может ничего купить:"
                + " нет товаров");
        productState.await();
      }

      int operate = operateProduct(-1);
      System.out.println("Покупатель купил 1 товар." +
          " Товаров на складе: " + operate);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(ex);
    } finally {
      productState.signalAll();
      locker.unlock();
    }
  }
  public void put() {
    try {
      locker.lock(); // potentially deadlock
      while(product >= 3) {
        System.out.println(
            "--- Производитель не может доставить товар:"
                + " склад полностью заполнен (максимум = 3)");
        productState2.await();
      }
      int operate = operateProduct(1);
      System.out.println("Производитель добавил 1 товар." +
          " Товаров на складе: " + operate);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(ex);
    } finally {
      productState2.signalAll();
      locker.unlock();
    }
  }

  private int operateProduct(int delta) {
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
