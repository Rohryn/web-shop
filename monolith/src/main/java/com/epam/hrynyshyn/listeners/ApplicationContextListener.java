package com.epam.hrynyshyn.listeners;

import com.epam.hrynyshyn.controllers.captcha.service.CaptchaCleaner;
import com.epam.hrynyshyn.controllers.captcha.service.CaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.DefaultCaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.providers.CaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.providers.CookieCaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.providers.HiddenCaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.providers.SessionCaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.storage.CaptchaCodes;
import com.epam.hrynyshyn.repository.TransactionManager;
import com.epam.hrynyshyn.repository.repositories.CategoryRepository;
import com.epam.hrynyshyn.repository.repositories.impl.CategoryRepositoryImpl;
import com.epam.hrynyshyn.repository.repositories.impl.ManufacturerRepositoryImpl;
import com.epam.hrynyshyn.repository.repositories.ManufacturerRepository;
import com.epam.hrynyshyn.repository.repositories.impl.OrderRepositoryImpl;
import com.epam.hrynyshyn.repository.repositories.OrderRepository;
import com.epam.hrynyshyn.repository.repositories.impl.ProductRepositoryImpl;
import com.epam.hrynyshyn.repository.repositories.ProductRepository;
import com.epam.hrynyshyn.repository.repositories.impl.UserRepositoryImpl;
import com.epam.hrynyshyn.repository.repositories.UserRepository;
import com.epam.hrynyshyn.services.CategoryService;
import com.epam.hrynyshyn.services.impl.CategoryServiceImpl;
import com.epam.hrynyshyn.services.impl.ManufacturerServiceImpl;
import com.epam.hrynyshyn.services.ManufacturerService;
import com.epam.hrynyshyn.services.impl.OrderServiceImpl;
import com.epam.hrynyshyn.services.OrderService;
import com.epam.hrynyshyn.services.impl.ProductServiceImpl;
import com.epam.hrynyshyn.services.ProductService;
import com.epam.hrynyshyn.services.impl.UserServiceImpl;
import com.epam.hrynyshyn.services.UserService;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_PROVIDER;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_STORING_MODE;
import static com.epam.hrynyshyn.constants.Constants.Captcha.COOKIE_STORING;
import static com.epam.hrynyshyn.constants.Constants.Captcha.HIDDEN_STORING;
import static com.epam.hrynyshyn.constants.Constants.Captcha.SESSION_STORING;
import static com.epam.hrynyshyn.constants.Constants.Services.CATEGORY_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.Services.MANUFACTURER_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.Services.ORDER_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.Services.PRODUCT_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.Services.USER_SERVICE;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    Logger logger = Logger.getLogger(ApplicationContextListener.class);

    private void databaseConnectivityInit(ServletContext context) {
        try {
            Context initContext = new InitialContext();
            Context environmentContext = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) environmentContext.lookup("jdbc/webshop");
            TransactionManager transactionManager = new TransactionManager(dataSource);

            UserRepository userRepository = new UserRepositoryImpl(transactionManager);
            ProductRepository productRepository = new ProductRepositoryImpl(transactionManager);
            CategoryRepository categoryRepository = new CategoryRepositoryImpl(transactionManager);
            ManufacturerRepository manufacturerRepository = new ManufacturerRepositoryImpl(transactionManager);
            OrderRepository orderRepository = new OrderRepositoryImpl(transactionManager);

            UserService userService = new UserServiceImpl(userRepository);
            ProductService productService = new ProductServiceImpl(productRepository);
            CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
            ManufacturerService manufacturerService = new ManufacturerServiceImpl(manufacturerRepository);
            OrderService orderService = new OrderServiceImpl(orderRepository);

            context.setAttribute(USER_SERVICE, userService);
            context.setAttribute(PRODUCT_SERVICE, productService);
            context.setAttribute(CATEGORY_SERVICE, categoryService);
            context.setAttribute(MANUFACTURER_SERVICE, manufacturerService);
            context.setAttribute(ORDER_SERVICE, orderService);
        } catch (NamingException e) {
            logger.error("Application initialize failure", e);
        }
    }

    private void captchaSupportInit(ServletContext context) {
        CaptchaService captchaService = new DefaultCaptchaService(new CaptchaCodes());
        String captchaMode = context.getInitParameter(CAPTCHA_STORING_MODE);
        Map<String, CaptchaProvider> providers = new HashMap<>();
        providers.put(SESSION_STORING, new SessionCaptchaProvider(captchaService));
        providers.put(COOKIE_STORING, new CookieCaptchaProvider(captchaService));
        providers.put(HIDDEN_STORING, new HiddenCaptchaProvider(captchaService));
        context.setAttribute(CAPTCHA_PROVIDER, providers.get(captchaMode));
        new CaptchaCleaner(context).startService();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        databaseConnectivityInit(servletContext);
        captchaSupportInit(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }


}
