package ch2_plain_java.sn2_default_methods;

/* -- see VIOLET notebook -- */
public class InterfacesInheritanceDemo {
  /*
  inheritance schema
  |---------------------------------------------------------------------|
  | Class2 <- Class1 <- Interface1 & Interface2                         |
  |---------------------------------------------------------------------|
  | ClassBoth2 <- ClassBoth <- InterfaceBoth <- Interface1 & Interface2 |
  |---------------------------------------------------------------------|
   */

  public static void main(String[] args) {
    checkClass2Hierarchy();
    checkClassBoth2Hierarchy();
  }

  private static void checkClass2Hierarchy() {
    System.out.println("Class2 checking:");
    System.out.println("----------------\n");

    Class1 c = new Class2();
    System.out.println(c.method1());
    System.out.println(c.method2());
    System.out.println(c.method3());
    System.out.println("----------------");

    Interface1 i = new Class2();
    System.out.println(i.method1());
    System.out.println(i.method2());
    System.out.println(i.method3());
    System.out.println("----------------");

    Interface2 i2 = new Class2();
    System.out.println(i2.method1());
    System.out.println(i2.method2());
    System.out.println(i2.method3());
    System.out.println("----------------\n\n");
  }

  private static void checkClassBoth2Hierarchy() {
    System.out.println("ClassBoth2 checking:");
    System.out.println("----------------\n");

    ClassBoth c = new ClassBoth2();
    System.out.println(c.method1());
    System.out.println(c.method2());
    System.out.println(c.method3());
    System.out.println(c.method4());
    System.out.println("----------------");

    Interface1 i = new ClassBoth2();
    System.out.println(i.method3());
    Interface2 i2 = new ClassBoth2();
    System.out.println(i2.method3());
    InterfaceBoth ib = new ClassBoth2();
    System.out.println(ib.method3());
  }
}
