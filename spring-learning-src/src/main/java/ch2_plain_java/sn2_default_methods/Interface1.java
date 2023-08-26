package ch2_plain_java.sn2_default_methods;

public interface Interface1 {

  String method1();

  default String method2(){
    return "Interface1.method2()";
  }

  default String method3() {return "Interface1.method3()";}

}
