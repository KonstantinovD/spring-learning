package book.p131_injecting_collections.set_bean;

import java.util.Set;

public class SetBean {
  private Set<String> nameSet;

  public SetBean(Set<String> strings) {
    this.nameSet = strings;
  }

  public void printNameSet() {
    System.out.println(nameSet);
  }
}
