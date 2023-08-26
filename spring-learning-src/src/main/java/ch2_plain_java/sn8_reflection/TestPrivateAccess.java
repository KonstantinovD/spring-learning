package ch2_plain_java.sn8_reflection;

import java.lang.reflect.Field;

public class TestPrivateAccess {
  public static void main(String[] args) {
    SampleClass sampleClass = new SampleClass("hello", 11.44);

    try {
      Field reflectField = SampleClass.class
          .getDeclaredField("s"); //NoSuchFieldException e
      Field reflectField2 = SampleClass.class
          .getDeclaredField("d");

      "hello".equals("he");

      // Если не дать доступ, то java.lang.IllegalAccessException
      reflectField.setAccessible(true);
      reflectField2.setAccessible(true);

      String fieldValue = (String) reflectField.get(sampleClass);
      Double fieldValue2 = (Double) reflectField2.get(sampleClass);

      show(fieldValue, reflectField, fieldValue2, reflectField2);


      // TEST PARENT CLASS PRIVATE MEMBERS

      EnhancedSample enhancedSample =
          new EnhancedSample("hi", 87.2, 14);
      Field reflectFieldEnhanced = EnhancedSample.class
          .getDeclaredField("i");
// EnhancedSample.class.getDeclaredField("s"); - NoSuchFieldException
      reflectFieldEnhanced.setAccessible(true);

      fieldValue = (String) reflectField.get(enhancedSample);
      fieldValue2 = (Double) reflectField2.get(enhancedSample);
      Integer fieldValue3 =
          (Integer) reflectFieldEnhanced.get(enhancedSample);

      show(fieldValue, reflectField, fieldValue2, reflectField2,
          fieldValue2, reflectFieldEnhanced);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();;
    }
  }

  private static void show(Object ... args) {
    for (Object o : args) {
      System.out.println(o);
    }
    System.out.println("-------------------\n");
  }
}
