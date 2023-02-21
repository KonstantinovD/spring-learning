package ch5_spring_security_in_action.p199_custom_filter_before_standard.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestValidationFilter implements Filter {

    public static final String REQUEST_ID = "Request-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = httpRequest.getHeader(REQUEST_ID);
        if (requestId == null || requestId.isBlank()) {
            // если header-а нет, то HTTP статус ставится 400, а request
            // не отправляется дальше в filter chain
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        // header есть, request перенаправляется дальше в filter chain
        filterChain.doFilter(request, response);
    }
}
