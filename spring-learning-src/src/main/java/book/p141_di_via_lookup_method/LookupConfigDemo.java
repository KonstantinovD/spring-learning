package book.p141_di_via_lookup_method;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

public class LookupConfigDemo {

  @Configuration
  @ComponentScan(basePackages = "book.p141_di_via_lookup_method")
  public static class LookupConfig{}

  public static void main(String[] args) {
    GenericApplicationContext cxt =
        new AnnotationConfigApplicationContext(LookupConfig.class);

    DemoBean specificBean =
        cxt.getBean("specificLookupBean", DemoBean.class);
    DemoBean standardBean =
        cxt.getBean("standardLookupBean", DemoBean.class);

    displayInfo("specificLookupBean", specificBean);
    // specificLookupBean: Singer Instances the Same? false
    // 1OOOOO gets took 179ms
    displayInfo("standardLookupBean", standardBean);
    // standardLookupBean: Singer Instances the Same? true
    // 1OOOOO gets took 0ms

    cxt.close();
  }

  public static void displayInfo(String beanName, DemoBean bean) {
    // проверка: один и тот же ооъект, либо Prototype?
    Singer singer1 = bean.getMySinger();
    Singer singer2 = bean.getMySinger();
    System.out.println(beanName + ": Singer Instances the Same? "
        + (singer1 == singer2));

    // тест производительности, чтобы выяснить, какой из компонентов
    // SpringBeans действует быстрее. Очевидно, что это будет
    // standardLookupBean, потому что всякий раз он возвращает один и
    // тот же экземпляр Singer
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("lookupDemo");
    for (int i = 0; i < 10000; i++) {
      Singer singer = bean.getMySinger();
      singer.sing();
    }
    stopWatch.stop();
    System.out.println("1OOOOO gets took "
        + stopWatch.getTotalTimeMillis() + "ms");
  }

}
