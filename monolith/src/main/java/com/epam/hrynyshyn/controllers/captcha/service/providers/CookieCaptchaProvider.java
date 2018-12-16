package com.epam.hrynyshyn.controllers.captcha.service.providers;

import com.epam.hrynyshyn.controllers.captcha.service.CaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_ID;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_LIFETIME;

/**
 * Captcha provider implementation, based on cookies.
 */
public class CookieCaptchaProvider extends AbstractCaptchaProvider {
    public CookieCaptchaProvider(CaptchaService service) {
        super(service);
    }

    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        Cookie cookie = new Cookie(CAPTCHA_ID, captcha.getId());
        cookie.setMaxAge(CAPTCHA_LIFETIME);
        response.addCookie(cookie);
    }

    @Override
    public Captcha getCaptchaFromStorage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CAPTCHA_ID)) {
                return service.getCaptcha(cookie.getValue());
            }
        }
        return null;
    }
}
