package ch5_spring_security_in_action.p210_OncePerRequestFilter_impl.filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter extends OncePerRequestFilter { // extends abstract class

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 @NonNull HttpServletResponse response, // @NonNull to prevent warning
                                 FilterChain filterChain) throws IOException, ServletException {
        String requestId = request.getHeader("Request-Id"); // получаем header
        log.info("Successfully authenticated request with id {}", requestId); // логируем его
        filterChain.doFilter(request, response);
    }
}
