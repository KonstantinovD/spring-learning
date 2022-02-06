package book.p131_injecting_collections;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionsBean {

  // Here, we declared the nameList property to hold a List of
  // String values. We use field injection for nameList.
  // Therefore, we put the @Autowired annotation.
  @Autowired
  private List<String> nameList;

  public void printNameList() {
    System.out.println(nameList);
  }
}
