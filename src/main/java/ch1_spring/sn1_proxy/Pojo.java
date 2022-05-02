package ch1_spring.sn1_proxy;

import org.springframework.stereotype.Component;

@Component
public class Pojo {

  @Loggable
  public void test(){
    System.out.println("test method called");
    this.testUtil();
  }

  @Loggable
  public void testUtil(){
    System.out.println("testUtil method called");
  }

  // @Loggable над testUtil() не заставит вызвать прокси два раза при вызове test()
  // Почему? Потому что testUtil() вызывается изнутри test() - метода класса Pojo
  // Т е прокси уже нет, прокся отрабатывает до вызова метода test(), но попадая
  // в метод test() мы уже находимся в оригинальном классе
}
