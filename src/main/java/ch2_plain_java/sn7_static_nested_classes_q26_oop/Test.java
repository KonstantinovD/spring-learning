package ch2_plain_java.sn7_static_nested_classes_q26_oop;

public class Test {

  public class InnerLocal {
    int v1;
    int v2;

    public int sum() {
      return v1 + v2 + v3;
    }

    public InnerLocal(int v1, int v2) {
      this.v1 = v1;
      this.v2 = v2;
    }
  }

  // Статические вложенные классы, не имеют доступа к нестатическим
  // полям и методам обрамляющего класса, что в некотором роде
  // аналогично статическим методам, объявленным внутри класса. Доступ
  // к нестатическим полям и методам может осуществляться только через
  // ссылку на экземпляр обрамляющего класса. В этом плане static
  // nested классы очень похожи на любые другие классы верхнего уровня.
  public static class StaticLocal {
    int v1;
    int v2;

    public int sum() {
      return v1 + v2;
    }
    
    public StaticLocal(int v1, int v2) {
      this.v1 = v1;
      this.v2 = v2;
    }
  }

  private InnerLocal local;
  private int v3;

  public Test(int a, int b) {
    this.local = new InnerLocal(a, b);
    this.v3 = 1000;
  }

  public InnerLocal getInnerClass() {
    return local;
  }
}
