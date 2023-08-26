package ch2_plain_java.sn2_default_methods;

public class ClassBoth2 extends ClassBoth {

  @Override
  public String method1() {
    return "ClassBoth2.method1()";
  }

  @Override
  public String method2() {
    return "ClassBoth2.method2()";
  }

  @Override
  public String method3() {
    return "ClassBoth2.method3() + " + super.method3();
  }

  @Override
  public String method4() {
    return "ClassBoth2.method4()";
  }
}
