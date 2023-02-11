package ch5_spring_security_in_action.p130_authenticationHandlers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("failed", LocalDateTime.now().toString());
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        // можем редиректнуть на логин повторно
        // httpServletResponse.sendRedirect("/login");
    }
}
