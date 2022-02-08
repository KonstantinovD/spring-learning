package book.p176_beans_autolinking;

import org.springframework.stereotype.Component;

// Режим constructor
@Component
public class ConstructorsTarget {

  Foo fooOne;
  Foo fooTwo;
  Bar bar;

  public ConstructorsTarget(Foo fooOne, Bar bar) {
    System.out.println("Target(Foo, Bar) called");
    this.fooOne = fooOne;
    this.bar = bar;
  }
}
