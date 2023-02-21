package ch5_spring_security_in_action.p199_custom_filter_before_standard.filters;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestId = httpRequest.getHeader("Request-Id"); // получаем header
        log.info("Successfully authenticated request with id {}", requestId); // логируем его
        filterChain.doFilter(request, response);
    }
}
