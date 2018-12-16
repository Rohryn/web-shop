package com.epam.hrynyshyn.listeners;

import com.epam.hrynyshyn.controllers.captcha.service.CaptchaCleaner;
import com.epam.hrynyshyn.controllers.captcha.service.CaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.DefaultCaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.providers.CaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.providers.CookieCaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.providers.HiddenCaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.providers.SessionCaptchaProvider;
import com.epam.hrynyshyn.controllers.captcha.service.storage.CaptchaCodes;
import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.category.CategoryRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.category.DefaultCategoryRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.manufacturer.DefaultManufacturerRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.manufacturer.ManufacturerRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.order.DefaultOrderRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.order.OrderRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.product.DefaultProductRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.product.ProductRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.user.DefaultUserRepository;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.user.UserRepository;
import com.epam.hrynyshyn.model.services.category.CategoryService;
import com.epam.hrynyshyn.model.services.category.DefaultCategoryService;
import com.epam.hrynyshyn.model.services.manufacturer.DefaultManufacturerService;
import com.epam.hrynyshyn.model.services.manufacturer.ManufacturerService;
import com.epam.hrynyshyn.model.services.order.DefaultOrderService;
import com.epam.hrynyshyn.model.services.order.OrderService;
import com.epam.hrynyshyn.model.services.product.DefaultProductService;
import com.epam.hrynyshyn.model.services.product.ProductService;
import com.epam.hrynyshyn.model.services.user.DefaultUserService;
import com.epam.hrynyshyn.model.services.user.UserService;
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

import static com.epam.hrynyshyn.constants.Constants.Captcha.*;
import static com.epam.hrynyshyn.constants.Constants.Services.*;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    Logger logger = Logger.getLogger(ApplicationContextListener.class);

    private void databaseConnectivityInit(ServletContext context) {
        try {
            Context initContext = new InitialContext();
            Context environmentContext = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) environmentContext.lookup("jdbc/webshop");
            TransactionManager transactionManager = new TransactionManager(dataSource);

            UserRepository userRepository = new DefaultUserRepository(transactionManager);
            ProductRepository productRepository = new DefaultProductRepository(transactionManager);
            CategoryRepository categoryRepository = new DefaultCategoryRepository(transactionManager);
            ManufacturerRepository manufacturerRepository = new DefaultManufacturerRepository(transactionManager);
            OrderRepository orderRepository = new DefaultOrderRepository(transactionManager);

            UserService userService = new DefaultUserService(userRepository);
            ProductService productService = new DefaultProductService(productRepository);
            CategoryService categoryService = new DefaultCategoryService(categoryRepository);
            ManufacturerService manufacturerService = new DefaultManufacturerService(manufacturerRepository);
            OrderService orderService = new DefaultOrderService(orderRepository);

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
