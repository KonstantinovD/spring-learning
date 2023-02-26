package ch5_spring_security_in_action.p235_cross_origin_resource_sharing.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MainController {
    // Defines a main.html page that makes the request
    @GetMapping("/")  // to the '/test' endpoint
    public String main() {
        return "p235_cross_origin_resource_sharing/p235_main.html";
    }
    // Defines an endpoint that we call from a different origin
    @CrossOrigin( // enabling cross-origin
        value = "http://localhost:8080",
        methods = {RequestMethod.POST})
    @PostMapping("/test") // to prove how CORS works
    @ResponseBody
    public String postHello() {
        log.info("Test method called");
        return "HELLO";
    }
}
