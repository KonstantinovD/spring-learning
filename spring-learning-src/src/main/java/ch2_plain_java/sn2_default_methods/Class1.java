package ch2_plain_java.sn2_default_methods;

public abstract class Class1 implements Interface1, Interface2 {

  // method1 non-default в обоих интерфейсах, переопределять не надо

  // конфликт, т к метод default хотя бы в одном из интерфейсов, надо переопределить
  // или объявить абстрактным
  public abstract String method2();
//  @Override
//  public String method2() {
//    return "Class1.method2() + " + Interface1.super.method2();
//  }

  // то же самое касается method3() - оба метода дефолтные - надо переопределить
  // или объявлять абстрактным
  @Override
  public String method3() {
    return "Class1.method3() + " + Interface2.super.method3() +
        " | " + Interface1.super.method3();
  }
}
