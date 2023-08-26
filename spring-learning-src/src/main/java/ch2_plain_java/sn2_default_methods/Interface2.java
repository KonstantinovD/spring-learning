package ch2_plain_java.sn2_default_methods;

public interface Interface2 {

  String method1();

  String method2();

  default String method3() {return "Interface2.method3()";}

}
