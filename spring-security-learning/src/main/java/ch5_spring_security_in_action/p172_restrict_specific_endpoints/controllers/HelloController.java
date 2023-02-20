package ch5_spring_security_in_action.p172_restrict_specific_endpoints.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin")
    public String admin() {
        return "Hello, admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "Hello, manager";
    }

    @GetMapping("/common")
    public String common() {
        return "Hello, common";
    }

}
