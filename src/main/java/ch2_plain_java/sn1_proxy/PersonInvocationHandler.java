package ch2_plain_java.sn1_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

// хотим, чтобы при вызове любого метода Person выводилось "Привет!"
public class PersonInvocationHandler implements InvocationHandler {
  private Person person;

  public PersonInvocationHandler(Person person) {
    this.person = person;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.printf("method to invoke: %s, args: %s\n",
        method.getName(), Arrays.toString(args));
    System.out.println("Привет!");
    return method.invoke(person, args);
  }
}
