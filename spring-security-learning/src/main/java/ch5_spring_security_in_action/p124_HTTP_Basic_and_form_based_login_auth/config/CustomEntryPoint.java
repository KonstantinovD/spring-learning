package ch5_spring_security_in_action.p124_HTTP_Basic_and_form_based_login_auth.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException, ServletException {
    response.addHeader("describe_reason", "UNAUTHORIZED_ACCESS");
    response.sendError(HttpStatus.UNAUTHORIZED.value());
  }
}
