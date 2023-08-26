package ch2_plain_java.sn1_proxy;

import java.lang.reflect.Proxy;

public class Main {
  public static void main(String[] args) {
    Man vasia = new Man("Вася", 30,
        "Минск", "Беларусь");

    //Получаем загрузчик класса у оригинального объекта
    ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

    //Получаем все интерфейсы, которые реализует оригинальный объект
    Class[] interfaces = vasiaClassLoader.getClass().getInterfaces();

    //Создаем прокси нашего объекта vasia
    Person proxy = (Person) Proxy.newProxyInstance(
        Person.class.getClassLoader(),
        vasiaClassLoader.getClass().getInterfaces(),
        new PersonInvocationHandler(vasia));

    //Вызываем у прокси объекта методы нашего оригинального объекта
    proxy.introduce();
    proxy.sayFrom();
  }
}
