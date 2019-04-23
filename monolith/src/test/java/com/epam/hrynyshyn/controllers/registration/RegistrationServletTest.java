package com.epam.hrynyshyn.controllers.registration;

import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;
import com.epam.hrynyshyn.controllers.captcha.service.providers.CaptchaProvider;
import com.epam.hrynyshyn.repository.TransactionManager;
import com.epam.hrynyshyn.services.UserService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.UUID;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_PROVIDER;
import static com.epam.hrynyshyn.constants.Constants.Services.USER_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.FIRST_NAME;
import static com.epam.hrynyshyn.constants.Constants.User.LAST_NAME;
import static com.epam.hrynyshyn.constants.Constants.User.PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.Validation.CONFIRM_PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.Validation.REGISTRATION_TIMEOUT;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
@PrepareForTest({RegistrationBeanValidator.class})
public class RegistrationServletTest {
    public static final int TWENTY_SECONDS = 20000;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ServletConfig config;
    @Mock
    private ServletContext context;
    @Mock
    private CaptchaProvider provider;

    private static UserService service;

    private RegistrationServlet servlet;

    private Captcha captcha;

    private ArgumentCaptor<HashMap> captor;

    @BeforeClass
    public static void configure() throws Exception {
        setUpUsersService();
    }

    private static void setUpUsersService() {
        TransactionManager transactionManager = new TransactionManager(getDataSource());
//        service = new UserServiceImpl(new UserRepositoryImpl(transactionManager));
    }

    private static DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/webshop");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Before
    public void setUp() throws Exception {
        prepareMocks();
        prepareRequest();
        prepareCaptcha();
        captor = ArgumentCaptor.forClass(HashMap.class);
        when(context.getAttribute(USER_SERVICE)).thenReturn(service);
        when(config.getServletContext()).thenReturn(context);
        Long registrationTimeout = System.currentTimeMillis() + TWENTY_SECONDS;
        when(session.getAttribute(REGISTRATION_TIMEOUT)).thenReturn(registrationTimeout);
        doNothing().when(response).sendRedirect(anyString());
        doNothing().when(session).setAttribute(anyString(), captor.capture());
        servlet = new RegistrationServlet();
        servlet.init(config);
    }

    private void prepareMocks() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        config = mock(ServletConfig.class);
        context = mock(ServletContext.class);
        provider = mock(CaptchaProvider.class);
    }

    private void prepareRequest() {
        when(request.getParameter(FIRST_NAME)).thenReturn("Ivan");
        when(request.getParameter(LAST_NAME)).thenReturn("Ivanov");
        when(request.getParameter(EMAIL)).thenReturn("correct@email.com");
        when(request.getParameter(PASSWORD)).thenReturn("123456789");
        when(request.getParameter(CONFIRM_PASSWORD)).thenReturn("123456789");
        when(request.getParameter(CAPTCHA)).thenReturn("123");
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(context);
    }

    private void prepareCaptcha() {
        captcha = new Captcha("123", "123", 123);
        when(context.getAttribute(CAPTCHA_PROVIDER)).thenReturn(provider);
        when(provider.getCaptchaFromStorage(request)).thenReturn(captcha);
    }

    @Test
    public void testRegistrationWithCorrectCaptcha() throws Exception {
        when(request.getParameter(CAPTCHA)).thenReturn("123");
        when(request.getParameter(EMAIL)).thenReturn(getUniqueEmail());
        int usersBefore = service.getCount();
        servlet.doPost(request, response);
        assertTrue(service.getCount() - usersBefore == 1);
    }

    @Test
    public void testRegistrationWithIncorrectCaptcha() throws Exception {
        when(request.getParameter(EMAIL)).thenReturn(getUniqueEmail());
        when(request.getParameter(CAPTCHA)).thenReturn("862359823");
        servlet.doPost(request, response);
        assertTrue(captor.getValue().size() == 1);
    }

    @Test
    public void testRegistrationWithUniqueEmail() throws Exception {
        when(request.getParameter(EMAIL)).thenReturn(getUniqueEmail());
        int usersBefore = service.getCount();
        servlet.doPost(request, response);
        assertTrue(service.getCount() - usersBefore == 1);
    }

    @Test
    public void testRegistrationWithReserved() throws Exception {
        when(request.getParameter(EMAIL)).thenReturn("reserved@email.com");
        servlet.doPost(request, response);
        assertTrue(captor.getValue().size() == 1);
    }

    private String getUniqueEmail() {
        return UUID.randomUUID().toString().substring(1, 10) + "@email.com";
    }
}