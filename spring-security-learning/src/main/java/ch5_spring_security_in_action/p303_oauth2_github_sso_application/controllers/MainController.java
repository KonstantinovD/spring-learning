package ch5_spring_security_in_action.p303_oauth2_github_sso_application.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String main(OAuth2AuthenticationToken token) {
        log.info("Received token: " + token);
        return "p303_oauth2_github_sso_application/p303_main.html";
    }
}
