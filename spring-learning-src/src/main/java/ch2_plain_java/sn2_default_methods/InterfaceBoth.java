package ch2_plain_java.sn2_default_methods;

public interface InterfaceBoth extends Interface1, Interface2 {

  String method2(); // but we can redefine method to non-implemented

  default String method3() { // or reimplement it
    return "InterfaceBoth.method3()";
  }

  String method4(); // own non-implemented method
}
