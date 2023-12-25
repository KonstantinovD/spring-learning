package spring.sn003_proxy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Man implements Person {
  private String name;
  private int age;
  private String city;
  private String country;

  public Man(String name, int age, String city, String country) {
    this.name = name;
    this.age = age;
    this.city = city;
    this.country = country;
  }

  @Override
  public void introduce() {
    System.out.println("Меня зовут " + this.name);
  }

  @Override
  public void sayAge() {
    System.out.println("Мне " + this.age + " лет");
  }

  @Override
  public void sayFrom() {
    System.out.println("Я из города " + this.city + ", " + this.country);
  }
}
