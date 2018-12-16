package com.epam.hrynyshyn.filters;


import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.User.SOURCE_PAGE_URL;
import static com.epam.hrynyshyn.constants.Constants.User.USER;

public class LoginFilter implements Filter {
    private Logger logger = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("do filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!userLoggedIn(request)) {
            redirectToLogin(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean userLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute(USER) != null;
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(SOURCE_PAGE_URL, request.getRequestURI());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {

    }

}
