package com.epam.hrynyshyn.controllers.captcha.service;

import com.epam.hrynyshyn.controllers.captcha.service.providers.CaptchaProvider;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_CLEANER_TIMEOUT;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_PROVIDER;

/**
 * Service for scheduled captcha storage cleaning. Runs as additional thread.
 */
public class CaptchaCleaner {
    private static Logger logger = Logger.getLogger(CaptchaCleaner.class);
    private ServletContext servletContext;

    public CaptchaCleaner(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void startService() {
        long captchaTimeout = Long.parseLong(servletContext.getInitParameter(CAPTCHA_CLEANER_TIMEOUT));
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            logger.info("cleaning captcha storage");
            ((CaptchaProvider) servletContext.getAttribute(CAPTCHA_PROVIDER)).removeExpiredCaptcha();
        }, 0, captchaTimeout, TimeUnit.MILLISECONDS);
    }

}
