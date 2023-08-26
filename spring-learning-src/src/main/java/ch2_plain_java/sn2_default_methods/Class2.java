package ch2_plain_java.sn2_default_methods;

public class Class2 extends Class1 {

  @Override
  public String method2() {

    /* Interface1.super.method2(); - IMPOSSIBLE */
    //
    // Because it violates encapsulation. You shouldn't be able to
    // bypass the parent class's behaviour.

    return "Class2.method2()";
  }

  @Override
  public String method1() {
    return "Class2.method1()";
  }
}
