package ch5_spring_security_in_action.p127_formLogin_homepage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // not @RestController
public class HelloController {

    @GetMapping("/home")
    public String home() {
      return "p127_home.html";
    }

  @GetMapping("/info")
  public String info() {
    return "p127_info.html";
  }
}
