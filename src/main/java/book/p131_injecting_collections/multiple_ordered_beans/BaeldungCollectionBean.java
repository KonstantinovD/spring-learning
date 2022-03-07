package book.p131_injecting_collections.multiple_ordered_beans;

import book.p131_injecting_collections.BaeldungBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaeldungCollectionBean {

  private final List<BaeldungBean> beanList;

  @Autowired(required = false)
  public BaeldungCollectionBean(List<BaeldungBean> beanList) {
    this.beanList = beanList;
  }

  public void printBeanList() {
    for (BaeldungBean b : beanList) {
      System.out.print(b.getName() + " ");
    }
    System.out.println();
  }
}
