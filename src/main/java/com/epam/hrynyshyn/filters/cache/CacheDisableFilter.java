package com.epam.hrynyshyn.filters.cache;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.Cache.*;

public class CacheDisableFilter implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader(CACHE_CONTROL, NO_CACHE);
        httpResponse.setDateHeader(EXPIRES, 0);
        httpResponse.setHeader(PRAGMA, NO_CACHE);
        chain.doFilter(request, response);
    }
}
