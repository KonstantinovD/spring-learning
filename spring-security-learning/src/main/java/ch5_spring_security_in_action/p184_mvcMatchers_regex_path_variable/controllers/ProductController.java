package ch5_spring_security_in_action.p184_mvcMatchers_regex_path_variable.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/product/{code}")
    public String productCode(@PathVariable String code) {
        return code;
    }

    @GetMapping("/regex/item/{code}")
    public String itemCode(@PathVariable String code) {
        return "regex item: " + code;
    }

    @GetMapping("/regex/Capital/{code}")
    public String capitalCode(@PathVariable String code) {
        return "Capital item: " + code;
    }
}
