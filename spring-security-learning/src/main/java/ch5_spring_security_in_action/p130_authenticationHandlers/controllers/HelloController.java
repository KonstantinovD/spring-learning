package ch5_spring_security_in_action.p130_authenticationHandlers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/home")
    public String home() {
        return "p130_authenticationHandlers/p130_home.html";
    }

    @GetMapping("/error")
    public String error() {
        return "p130_authenticationHandlers/p130_error.html";
    }

}
