package com.epam.hrynyshyn.filters.security.dispatchers;


import com.epam.hrynyshyn.exceptions.ConfigurationFailureException;
import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;
import com.epam.hrynyshyn.filters.security.configuration.SecurityConstraint;
import com.epam.hrynyshyn.model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.User.USER;

public class RoleAccessDispatcher extends AbstractAccessDispatcher {
    public RoleAccessDispatcher(ConstraintStorage constraintStorage) {
        super(constraintStorage);
    }

    @Override
    protected boolean isAccessible(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        SecurityConstraint constraint = getSecurityConstraint(request);

        return constraint.getRoles().contains(user.getRole());
    }

    private SecurityConstraint getSecurityConstraint(HttpServletRequest request) {
        String URL = request.getRequestURL().toString();
        List<SecurityConstraint> constraints = constraintStorage.getConstraints();
        for (SecurityConstraint constraint : constraints) {
            if (URL.matches(constraint.getUrlPattern())) {
                return constraint;
            }
        }
        throw new ConfigurationFailureException("Unexpected constraints changes");
    }

    @Override
    protected void doPositive(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    protected void doNegative(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.sendRedirect("../accessDenied.do");
    }
}
