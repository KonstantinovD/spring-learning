package ch5_spring_security_in_action.p387_pre_post_authorization.controllers;

import ch5_spring_security_in_action.p387_pre_post_authorization.model.Employee;
import ch5_spring_security_in_action.p387_pre_post_authorization.service.BookService;
import ch5_spring_security_in_action.p387_pre_post_authorization.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private NameService nameService;

    @Autowired
    private BookService bookService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello, " + nameService.getName();
    }

    @GetMapping("/secret/names/{name}")
    public List<String> names(@PathVariable String name) {
        return nameService.getSecretNames(name);
    }

    @GetMapping("/book/details/{name}")
    public Employee getDetails(@PathVariable String name) {
        return bookService.getBookDetails(name);
    }
}
