package com.epam.hrynyshyn.filters.security.dispatchers;

import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.User.SOURCE_PAGE_URL;
import static com.epam.hrynyshyn.constants.Constants.User.USER;


public class UserAccessDispatcher extends AbstractAccessDispatcher {
    public UserAccessDispatcher(ConstraintStorage constraintStorage) {
        super(constraintStorage);
    }

    @Override
    protected boolean isAccessible(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute(USER) != null;
    }

    @Override
    protected void doPositive(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        nextDispatcher.checkAccess(request, response, chain);
    }

    @Override
    protected void doNegative(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute(SOURCE_PAGE_URL, request.getRequestURI());
        response.sendRedirect("../login.do");
    }
}
