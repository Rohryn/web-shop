package com.epam.hrynyshyn.filters.locale.service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_TIMEOUT;
import static com.epam.hrynyshyn.constants.Constants.Locale.LOCALE;

public class CookieLocaleStorage implements LocaleStorage {
    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie(LOCALE, locale.toString());
        int cookieMaxAge = Integer.parseInt(request.getServletContext().getInitParameter(CAPTCHA_TIMEOUT));
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }

    @Override
    public Locale retrieveLocale(HttpServletRequest request) {
        Locale locale = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(LOCALE)) {
                locale = new Locale(cookie.getValue());
                break;
            }
        }
        return locale;
    }
}
