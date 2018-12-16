package com.epam.hrynyshyn.filters.locale.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static com.epam.hrynyshyn.constants.Constants.Locale.LOCALE;

public class SessionLocaleStorage implements LocaleStorage {

    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE, locale);
    }

    @Override
    public Locale retrieveLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Locale) session.getAttribute(LOCALE);
    }
}
