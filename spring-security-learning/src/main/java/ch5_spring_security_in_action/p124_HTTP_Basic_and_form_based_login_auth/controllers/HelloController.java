package ch5_spring_security_in_action.p124_HTTP_Basic_and_form_based_login_auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
      return "Hello";
    }
}
