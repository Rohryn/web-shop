package com.epam.hrynyshyn.controllers.captcha.service.providers;

import com.epam.hrynyshyn.controllers.captcha.service.CaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_ID;

/**
 * Captcha provider implementation, based on storing captcha id in hidden field in form.
 */
public class HiddenCaptchaProvider extends AbstractCaptchaProvider {
    @Override
    protected void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        logger.info("storing " + captcha.getId());
        request.getSession().setAttribute(CAPTCHA_ID, captcha.getId());
    }

    @Override
    public Captcha getCaptchaFromStorage(HttpServletRequest request) {
        logger.info("got " + request.getParameter(CAPTCHA_ID));
        return service.getCaptcha((String) request.getSession().getAttribute(CAPTCHA_ID));
    }

    public HiddenCaptchaProvider(CaptchaService service) {
        super(service);
    }
}
