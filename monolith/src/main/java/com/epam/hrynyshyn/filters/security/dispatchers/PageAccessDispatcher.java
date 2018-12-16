package com.epam.hrynyshyn.filters.security.dispatchers;

import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;
import com.epam.hrynyshyn.filters.security.configuration.SecurityConstraint;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PageAccessDispatcher extends AbstractAccessDispatcher {

    public PageAccessDispatcher(ConstraintStorage constraintStorage) {
        super(constraintStorage);
    }

    @Override
    protected boolean isAccessible(HttpServletRequest request) {
        return !isURLSecured(request.getRequestURL().toString());
    }

    private boolean isURLSecured(String URL) {
        List<SecurityConstraint> constraints = constraintStorage.getConstraints();
        for (SecurityConstraint constraint : constraints) {
            if (URL.matches(constraint.getUrlPattern())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doPositive(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    protected void doNegative(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        nextDispatcher.checkAccess(request, response, chain);
    }
}
