package book.p176_beans_autolinking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;


public class TrickyTarget {

  Foo fooOne;
  Foo fooTwo;
  Foo fooThree;
  // because @Component("kitchen") - not "bar" (by default),
  // linking by type is used
  Bar bar;

  // When there are multiple beans available of same type in Spring
  // container, all of them are qualified to be autowired to
  // single-valued dependency. That causes ambiguity and leads to throw
  // an exception by framework.
  //
  // @Primary indicates that a bean should be given preference when
  // multiple candidates are qualified to autowire a single-valued
  // dependency.
  //
  // @Primary can be used both with @Bean and @Component(and so on)
  @Configuration
  @ComponentScan("book.p176_beans_autolinking")
  static class TargetConfig {
    @Primary // this bean will be chosen instead of beans below
    @Bean
    public Foo fooImplOne() {
      return new FooImplOne();
    }

    @Bean
    public Foo fooImplTwo() {
      return new FooImplTwo();
    }

    @Bean
    public Foo fooImplThree() {
      return new FooImplThree();
    }

    @Bean
    public Bar bar() {
      return new Bar();
    }

    @Bean
    public TrickyTarget trickyTarget() {
      return new TrickyTarget();
    }
  }

  @Autowired
  public void setFooOne(Foo fooOne) {
    this.fooOne = fooOne;
    System.out.println("Property fooOne set, class: " +
        fooOne.getClass());
  }

  @Autowired
  public void setFooTwo(Foo foo) {
    this.fooTwo = foo;
    System.out.println("Property fooTwo set, class: " +
        fooTwo.getClass());
  }

  @Autowired
  public void setFooThree(Foo fooThree) {
    this.fooThree = fooThree;
    System.out.println("Property fooThree set, class: " +
        fooThree.getClass());
  }

  @Autowired
  public void setBar(Bar bar) {
    this.bar = bar;
    System.out.println("Property bar set");
  }

  public static void main(String[] args) {
    GenericApplicationContext ctx =
        new AnnotationConfigApplicationContext(
            TargetConfig.class);
    TrickyTarget t = ctx.getBean(TrickyTarget.class);

    // check Режим constructor (no @Autowired)
    // enabled since Spring4.3
    // More than 1 class constructor isn't available (or use @Autowired)
    ConstructorsTarget ct = ctx.getBean(ConstructorsTarget.class);
    System.out.println("CHECK: ct.bar == null? " + (ct.bar == null)
        + " | ct.fooOne == null? " + (ct.fooOne == null)
        + " | ct.fooTwo == null? " + (ct.fooTwo == null));
    // CHECK: ct.bar == null? false | ct.fooOne == null? false | ct.fooTwo == null? true
    ctx.close();
  }

}
