package book.p131_injecting_collections.map_bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "book.p131_injecting_collections.map_bean")
public class MapConfig {

// MapBean - @Component

  @Bean
  public Map<Integer, String> nameMap() {
    Map<Integer, String>  nameMap = new HashMap<>();
    nameMap.put(1, "John");
    nameMap.put(2, "Adam");
    nameMap.put(3, "Harry");
    return nameMap;
  }

  public static void main(String[] args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(
            MapConfig.class);
    MapBean mapBean = context.getBean(MapBean.class);
    mapBean.printNameMap(); // {1=John, 2=Adam, 3=Harry}
  }

}
