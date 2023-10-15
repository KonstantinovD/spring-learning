package ch6_hibernate.p75_bean_hibernate_validator.controller;

import ch6_hibernate.p75_bean_hibernate_validator.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Slf4j
@Controller // Для @Controller/@RestController аннотация @Validated не нужна
public class HibernateValidatorController {

    @PostMapping("/validate")
    public String validate(@Valid @RequestBody Item item) {
        return "done";
    }

}
