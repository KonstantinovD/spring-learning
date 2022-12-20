package ch2_plain_java.sn12_strings;

public class Main {

  // TODO: статьи про строки и автокомпрессию:
  //  https://itsobes.ru/JavaSobes/iz-chego-sostoit-string/
  //  https://itsobes.ru/JavaSobes/kak-oboiti-strochku/

  public static void main(String[] args) {
    String val = "Hello";
    String rus_val = "Привет";
    // 5 а не 10 - по 1 byte на символ - автокомпрессия
    System.out.println(val.getBytes().length);
    // 12 а не 6 - по 2 byte (1 char) на символ
    System.out.println(rus_val.getBytes().length);
    // выведет 7 байтов - но по факту их 12, просто английские буквы содержат '0' в старшем байте
    String rus_eng_val_2 = "HelloЯ";
    System.out.println(rus_eng_val_2.getBytes().length);
    int k = 0;
  }

}
