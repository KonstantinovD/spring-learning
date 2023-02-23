package ch5_spring_security_in_action.p217_CsrfToken_logging.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CsrfTokenLogger implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        log.info("CSRF token: {}", token.getToken());
        filterChain.doFilter(request, response);
    }
}
