package ch5_spring_security_in_action.p220_CsrfToken_in_web_forms.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        log.info("Adding product " + name);
        return "p220_CsrfToken_in_web_forms/p220_main.html";
    }
}
