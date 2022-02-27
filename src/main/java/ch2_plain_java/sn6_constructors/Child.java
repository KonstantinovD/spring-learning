package ch2_plain_java.sn6_constructors;

public class Child extends Parent {

  int cv;

  // даже если у парента есть конструктор с параметрами, но объявлен
  // и конструктор без параметров, можно не вызывать super()
  public Child(int cv) {
    this.cv = cv;
  }

}
