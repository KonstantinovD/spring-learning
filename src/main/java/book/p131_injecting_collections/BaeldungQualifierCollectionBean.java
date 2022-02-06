package book.p131_injecting_collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaeldungQualifierCollectionBean {

  @Autowired
  @Qualifier("collectionsBean")
  private List<BaeldungBean> beanList;

  public void printBeanList() {
    for (BaeldungBean b : beanList) {
      System.out.print(b.getName() + " ");
    }
    System.out.println();
  }
}
