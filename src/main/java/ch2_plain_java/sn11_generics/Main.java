package ch2_plain_java.sn11_generics;

import java.util.ArrayList;
import java.util.List;

// heap pollution example
public class Main {

  public static void main(String[] args) {
    // heap pollution example
    List<Integer> ls1 = new ArrayList<Integer>();
    ls1.add(12); // heap pollution, если бы добавления не было - то все бы работало нормально
    List<String> strings = (List) ls1;
    strings.add("hello");
    for (String s : strings) {
      System.out.println(s);
    }

    //TODO: смотри статью link:https://habr.com/ru/company/sberbank/blog/416413/

    Class<Integer> clazz = Integer.class;
    Integer integer = 123;
//    Class<Integer> clazz2 = integer.getClass(); - нельзя, возвращается <? extends Integer>
    Class<? extends Integer> clazz2 = integer.getClass();
//    Почему? потому что integer.getClass() должно содержать в себе данные о том, что это
//    Integer, Number,.. Object | НО! generics инварианты;
//    если бы мы возвращали Class<Integer>, то не имели бы данных о Number, Object - только Integer
//    Поэтому для сохранения ковариантности выбираем <? extends Integer>
  }

}
