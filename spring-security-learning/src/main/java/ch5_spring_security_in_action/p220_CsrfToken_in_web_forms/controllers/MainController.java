package ch5_spring_security_in_action.p220_CsrfToken_in_web_forms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "p220_CsrfToken_in_web_forms/p220_main.html";
    }

}
