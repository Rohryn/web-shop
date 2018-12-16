package com.epam.hrynyshyn.filters.gzip;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.Compression.*;

public class CompressingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isContentCompressible(request)) {
            compressContent(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isContentCompressible(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader(ACCEPT_ENCODING);
        return acceptEncoding != null && acceptEncoding.contains(GZIP);
    }

    private void compressContent(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.addHeader(CONTENT_ENCODING, GZIP);
        GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(response);
        filterChain.doFilter(request, gzipResponse);
        gzipResponse.close();
    }


    @Override
    public void destroy() {

    }
}
