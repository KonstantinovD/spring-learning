package ch1_spring.sn1_proxy;

import org.springframework.stereotype.Component;

@Component
public class Pojo {

  @Loggable
  public void test(){
    System.out.println("test method called");
    this.testUtil();
  }

  @Loggable
  public void testUtil(){
    System.out.println("testUtil method called");
  }

}
