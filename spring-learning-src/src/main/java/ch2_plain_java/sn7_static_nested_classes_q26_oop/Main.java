package ch2_plain_java.sn7_static_nested_classes_q26_oop;

public class Main {
  public static void main(String[] args) {
    Test t = new Test(12, 13);
    Test.InnerLocal local = t.getInnerClass();
    System.out.println(local.sum());

    Test.StaticLocal staticLocal1 = new Test.StaticLocal(25, 30);
    Test.StaticLocal staticLocal2 = new Test.StaticLocal(11, 11);

    System.out.println(staticLocal1.sum() + " " + staticLocal2.sum());
  }
}
