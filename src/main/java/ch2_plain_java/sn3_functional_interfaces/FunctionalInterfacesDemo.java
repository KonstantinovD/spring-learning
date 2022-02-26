package ch2_plain_java.sn3_functional_interfaces;

public class FunctionalInterfacesDemo {
  public static void main(String[] args) {
    executeCustom(3, (a, b, c) -> (long)(a + b + c));
    executeCustom(14, (a, b, c) -> (long)(a + b + c));
  }

  private static void executeCustom(
      int a, TripleFunction<Integer, Long> lambda) {
    System.out.println(lambda.apply(a, a, a));
  }
}
