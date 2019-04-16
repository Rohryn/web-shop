package com.epam.hrynyshyn.filters.security;

import com.epam.hrynyshyn.filters.security.configuration.ConfigurationFileParser;
import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;
import com.epam.hrynyshyn.filters.security.dispatchers.AccessDispatcher;
import com.epam.hrynyshyn.filters.security.dispatchers.AccessDispatcherChain;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.Security.CONFIGURATION;

public class SecurityFilter implements Filter {
    private ConstraintStorage securityConstraints;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String configurationFilePath = filterConfig.getInitParameter(CONFIGURATION);
        ConfigurationFileParser parser = new ConfigurationFileParser(configurationFilePath);

        securityConstraints = new ConstraintStorage(parser.getSecurityConstraints());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        AccessDispatcherChain chain = new AccessDispatcherChain(securityConstraints);
        AccessDispatcher dispatcher = chain.constructDispatcherChain();
        dispatcher.checkAccess(request, response, filterChain);
    }

    @Override
    public void destroy() {

    }
}
