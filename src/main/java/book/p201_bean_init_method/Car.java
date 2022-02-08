package book.p201_bean_init_method;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@NoArgsConstructor
@Data
public class Car implements InitializingBean, BeanNameAware {

  public static final int DEFAULT_MAX_SPEED = 150;

  private Integer maxSpeed;
  private String name;
  private boolean isGasolineEngine;
  private String beanName;
  private String beanNamePrefix;

  public Car(Integer maxSpeed, String name, boolean isGasolineEngine) {
    this.maxSpeed = maxSpeed;
    this.name = name;
    this.isGasolineEngine = isGasolineEngine;
  }

  // Метод инициализации делает приложения независимым от каркаса Spring,
  // но при этом надо конфигурить метод инициализации для каждого Bean,
  // который в нем нуждается.
  // Интерфейс InitializingBean позволяет указывать обратный вызов при
  // инициализации один раз для всех экземпляров класса компонента Bean,
  // но это привязывает приложение к каркасу Spring.
  // Аннотации применяются над методами; следует проверять, поддерживает
  // ли контейнер IoC спецификацию JSR-250.

  @Override
  public void setBeanName(String beanName) {
    this.beanName = beanName;
    this.beanNamePrefix = "[" + this.beanName + "] ";
  }

  // Чтобы исключить лишние внешние вызовы метода инициализации, его
  // необходимо объявить private. Контейнер IoC сможет вызвать метод
  // инициализации через рефлексию.
  private void init() {
    System.out.println(beanNamePrefix + "call init() method");
    if (!StringUtils.hasText(this.name)) {
      System.out.println(beanNamePrefix + "ERROR: You must set " +
          "the name property of any beans of Car type!");
    }
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println(beanNamePrefix
        + "call afterPropertiesSet() InitializingBean method");
    if (this.maxSpeed == null) {
      this.maxSpeed = DEFAULT_MAX_SPEED;
      System.out.println(beanNamePrefix + "set default maxSpeed");
    } else {
      System.out.println(beanNamePrefix + "maxSpeed is already set");
    }
  }

  @PostConstruct
  public void postProcess() {
    System.out.println(beanNamePrefix + "call @PostConstruct method");
    if (this.isGasolineEngine) {
      System.out.println(beanNamePrefix + "Car with gasoline engine");
    }
  }

  @Override
  public String toString() {
    return "Car{" +
        "maxSpeed=" + maxSpeed +
        ", name='" + name + '\'' +
        ", isGasolineEngine=" + isGasolineEngine +
        '}';
  }
}
