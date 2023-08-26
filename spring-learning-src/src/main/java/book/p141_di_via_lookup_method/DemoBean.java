package book.p141_di_via_lookup_method;

public interface DemoBean {
  // применяется для получения ссылки на экземпляр типа Singer,
  // а если это компонент Spring Bean с методом поиска, то для
  // выполнения конкретного поиска.
  Singer getMySinger();

  void doSomething();
}
