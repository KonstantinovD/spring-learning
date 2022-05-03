package ch2_plain_java.sn5_locks_with_conditions;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockWithConditionsStackDemo {

  Stack<String> stack = new Stack<>();
  int CAPACITY = 5;

  ReentrantLock lock = new ReentrantLock();
  Condition stackEmptyCondition = lock.newCondition();
  Condition stackFullCondition = lock.newCondition();

  public static void main(String[] args) {
    LockWithConditionsStackDemo lwc = new LockWithConditionsStackDemo();
    new Thread(() -> {
      for (int i = 1; i < 6; i++) {
        int val = i;
        new Thread(() -> {
          try {
            lwc.pushToStack(String.valueOf(val));
          } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
          }
        }).start();
      }
    }).start();
    new Thread(() -> {
      for (int i = 1; i < 6; i++) {
        new Thread(() ->
        {
          try {
            System.out.println("Received: " + lwc.popFromStack());
          } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
          }
        }).start();
      }
    }).start();
  }

  public void pushToStack(String item) throws Exception{
    try {
      lock.lock();
      while(stack.size() == CAPACITY) {
        stackFullCondition.await();
      }
      stack.push(item);
      System.out.println("pushed: " + item);
      stackEmptyCondition.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public String popFromStack() throws Exception {
    try {
      lock.lock();
      while(stack.size() == 0) {
        stackEmptyCondition.await();
      }
      String popped = stack.pop();
      System.out.println("popped: " + popped);
      return popped;
    } finally {
      stackFullCondition.signalAll();
      lock.unlock();
    }
  }

}
