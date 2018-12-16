package com.epam.hrynyshyn.controllers.captcha.service.providers;

import com.epam.hrynyshyn.controllers.captcha.service.CaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA;

/**
 * Captcha provider implementation, based on storing captcha in session.
 */
public class SessionCaptchaProvider extends AbstractCaptchaProvider {
    @Override
    protected void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        request.getSession().setAttribute(CAPTCHA, captcha);
    }

    @Override
    public Captcha getCaptchaFromStorage(HttpServletRequest request) {
        return (Captcha) request.getSession().getAttribute(CAPTCHA);
    }

    public SessionCaptchaProvider(CaptchaService service) {
        super(service);
    }
}
