package book.p239_property_editors;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class PropertyEditorBean {

  private byte[] bytes; // ByteArrayPropertyEditor
  private Character character; // CharacterEditor
  private Class cls; // ClassEditor
  private Boolean trueOrFalse; // CustomBooleanEditor
  private List<String> stringList; // CustomCollectionEditor
  private Date date; // CustomDateEditor
  private Float floatValue; // CustomNumЬerEditor
  private File file; // FileEditor
  private InputStream stream; // InputStreamEditor
  private Locale locale; // LocaleEditor
  private Pattern pattern; // PatternEditor
  private Properties properties; // PropertiesEditor
  private String trimString; // StringTrimmerEditor
  private URL url; // URLEditor

  public static class CustomPropertyEditorRegistrar
      implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(
        PropertyEditorRegistry registry) {
      SimpleDateFormat dateFormatter =
          new SimpleDateFormat("MM/dd/yyyy");
      registry.registerCustomEditor(Date.class,
          new CustomDateEditor(dateFormatter, true));
      registry.registerCustomEditor(String.class,
          new StringTrimmerEditor(true));
    }
  }

  public static void main(String[] args) throws Exception{
    File file = File.createTempFile("test", "txt");
    file.deleteOnExit();

    GenericXmlApplicationContext ctx =
        new GenericXmlApplicationContext();
    ctx.load(new ClassPathResource(
        "spring/p239_Property_editors/app-property-context.xml"));
    ctx.refresh();

    PropertyEditorBean bean =
        (PropertyEditorBean)ctx.getBean("builtInSample");
    ctx.close();
  }

  public void setBytes(byte[] bytes) {
    System.out.println("Setting bytes: " + Arrays.toString(bytes));
    this.bytes = bytes;
  }

  public void setCharacter(Character character) {
    System.out.println("Setting character: "+ character);
    this.character = character;
  }

  public void setCls(Class cls) {
    System.out.println("Setting class: "+ cls.getName());
    this.cls = cls;
  }

  public void setTrueOrFalse(Boolean trueOrFalse) {
    System.out.println("Setting Boolean: " + trueOrFalse);
    this.trueOrFalse = trueOrFalse;
  }

  public void setStringList(List<String> stringList) {
    System.out.println("Setting string list with size: "
        + stringList.size());
    this.stringList = stringList;
    for (String s : stringList) {
      System.out.println("Setting member: " + s);
    }
  }

  public void setDate(Date date) {
    System.out.println("Setting date: " + date);
    this.date = date;
  }

  public void setFloatValue(Float floatValue) {
    System.out.println("Setting Float: " + floatValue);
    this.floatValue = floatValue;
  }

  public void setFile(File file) {
    System.out.println("Setting file: " + file.getName());
    this.file = file;
  }

  public void setLocale(Locale locale) {
    System.out.println("Setting locale: " + locale.getDisplayName());
    this.locale = locale;
  }

  public void setPattern(Pattern pattern) {
    System.out.println("Setting pattern: " + pattern);
    this.pattern = pattern;
  }

  public void setProperties(Properties properties) {
    System.out.println("Loaded " + properties.size() + " properties");
    this.properties = properties;
  }

  public void setTrimString(String trimString) {
    System.out.println("Setting trim string: " + trimString);
    this.trimString = trimString;
  }

  public void setUrl(URL url) {
    System.out.println("Setting URL: " + url.toExternalForm());
    this.url = url;
  }
}
