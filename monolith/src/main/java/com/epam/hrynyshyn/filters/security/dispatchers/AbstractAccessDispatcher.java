package com.epam.hrynyshyn.filters.security.dispatchers;

import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractAccessDispatcher implements AccessDispatcher {
    protected AbstractAccessDispatcher nextDispatcher;
    protected ConstraintStorage constraintStorage;

    public AbstractAccessDispatcher(ConstraintStorage constraintStorage) {
        this.constraintStorage = constraintStorage;
    }

    public void setNextDispatcher(AbstractAccessDispatcher nextDispatcher) {
        this.nextDispatcher = nextDispatcher;
    }

    @Override
    public void checkAccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isAccessible(request)) {
            doPositive(request, response, chain);
        } else {
            doNegative(request, response, chain);
        }
    }

    protected abstract boolean isAccessible(HttpServletRequest request);

    protected abstract void doPositive(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    protected abstract void doNegative(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
}
