package ch5_spring_security_in_action.p153_Authorization_RestrictingAccess.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/info")
    public String info() {
        return "Application for checking authorities";
    }

    @GetMapping("/read")
    public String read() {
        return "There is nothing to read :(";
    }

    @GetMapping("/write")
    public String write() {
        return "Writing... \n\nAll the DB would have been rewritten... if there was any DB \n^_^\nbut there is nothing";
    }

    @GetMapping("/notify")
    public String notification() {
        return "We've got your notification";
    }

    @GetMapping("/aboutYou")
    public String aboutYou() {
        return "Hello, " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/dangerous") // to test denyAll() method
    public String dangerous() {
        return "This is a strictly secured information and you aren't allowed to view it!!!";
    }
}
