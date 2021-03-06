package book.p131_injecting_collections.map_bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapBean {
  private Map<Integer, String> nameMap;

  @Autowired
  public void setNameMap(Map<Integer, String> nameMap) {
    this.nameMap = nameMap;
  }

  public void printNameMap() {
    System.out.println(nameMap);
  }
}
