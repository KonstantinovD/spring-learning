package book.p155_beans_naming;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

public class BeanNamingTest {

  public static void main(String[] args) {
    GenericXmlApplicationContext ctx =
        new GenericXmlApplicationContext();
    ctx.load(new ClassPathResource(
        "spring/p155_beans_naming/app-context-01.xml"));
    ctx.refresh();


    Map<String, String> beans =
        ctx.getBeansOfType(String.class);
    beans.forEach(
        (k, v) -> System.out.println(k + " | " + v));
    // string1 |
    // string2 |
    // basicName |
    // java.lang.String#0 |
    // java.lang.String#1 |

    // empty after |, because blank string = ""

    String s1 = (String)ctx.getBean("string3");
    String s2 = (String)ctx.getBean("basicName");
    String s3 = (String)ctx.getBean("stringLast");
    // assert the same object
    System.out.println("Links to the same object? "
        + (s1 == s2 && s2 == s3));

    System.out.println(s1.isEmpty());    // true
    System.out.println(s1.isBlank());    // true

    ctx.close();
  }
}
