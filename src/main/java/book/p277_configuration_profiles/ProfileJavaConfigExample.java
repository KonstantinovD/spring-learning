package book.p277_configuration_profiles;

import book.p277_configuration_profiles.config.FoodConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
@PropertySource("classpath:application.properties")
public class ProfileJavaConfigExample {

  @Autowired
  private Environment environment;

  public static void main(String[] args) {
    GenericApplicationContext ctx =
        new AnnotationConfigApplicationContext(FoodConfig.class);

    ProfileJavaConfigExample configExample =
        ctx.getBean(ProfileJavaConfigExample.class);
    System.out.println("Active profile: "
        + Arrays.toString(configExample.getEnvironment().getActiveProfiles()));

    FoodProviderService foodProviderService =
        ctx.getBean(FoodProviderService.class);
    List<Food> lunchSet = foodProviderService.provideLunchSet();
    for (Food food : lunchSet) {
      System.out.println("Food: "+ food.getName());
    }
    ctx.close();
  }
}
