package testing;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestApp {



  public static void main(String[] args) {
    method(1.2);
    method((Integer) null);
  }

  public static void method(Object o) {
    System.out.println("Object method");
  }

  public static void method(Integer i) {
    System.out.println("Integer method");
  }

  public static void method(String s) {
    System.out.println("String method");
  }
}
