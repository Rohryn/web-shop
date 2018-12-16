package com.epam.hrynyshyn.filters.security.dispatchers;

import com.epam.hrynyshyn.filters.security.configuration.ConfigurationFileParser;
import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;
import com.epam.hrynyshyn.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.hrynyshyn.constants.Constants.User.USER;
import static org.mockito.Mockito.*;

public class AccessDispatcherChainTest {
    private AccessDispatcher accessDispatcher;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private FilterChain filterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ConstraintStorage constraintStorage = prepareConstraintStorage();
        AccessDispatcherChain dispatcherChain = new AccessDispatcherChain(constraintStorage);
        accessDispatcher = dispatcherChain.constructDispatcherChain();
        when(request.getSession()).thenReturn(session);
        doNothing().when(filterChain).doFilter(request, response);

    }

    private ConstraintStorage prepareConstraintStorage() {
        ConfigurationFileParser parser = new ConfigurationFileParser("src/main/webapp/META-INF/security.xml");
        return new ConstraintStorage(parser.getSecurityConstraints());
    }

    @Test
    public void testUnSecuredPage() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("localhost:8080/BikeShop/register.jsp"));
        accessDispatcher.checkAccess(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testSecuredPageAndNoUser() throws Exception {
        configureSecuredPage();
        when(session.getAttribute(USER)).thenReturn(null);
        accessDispatcher.checkAccess(request, response, filterChain);
        verify(response, times(1)).sendRedirect("../login.do");
    }

    @Test
    public void testSecuredPageWithSimpleUser() throws Exception {
        configureSecuredPage();
        setupAuthorizedUser();
        accessDispatcher.checkAccess(request, response, filterChain);
        verify(session, times(2)).getAttribute(USER);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    private void setupAuthorizedUser() {
        User user = new User();
        user.setRole("user");
        when(session.getAttribute(USER)).thenReturn(user);
    }

    @Test
    public void testSecuredPageWithUnauthorizedUser() throws Exception {
        configureSecuredPage();
        setupUnauthorizedUser();
        accessDispatcher.checkAccess(request, response, filterChain);
        verify(response, times(1)).sendRedirect("../accessDenied.do");
    }

    private void setupUnauthorizedUser() {
        User user = new User();
        user.setRole("undefinedRole");
        when(session.getAttribute(USER)).thenReturn(user);
    }

    private void configureSecuredPage() {
        when(request.getRequestURL()).thenReturn(new StringBuffer("localhost:8080/BikeShop/order/register.jsp"));
    }

}