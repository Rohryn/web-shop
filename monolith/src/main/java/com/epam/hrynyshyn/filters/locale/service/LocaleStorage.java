package com.epam.hrynyshyn.filters.locale.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public interface LocaleStorage {
    void store(HttpServletRequest request, HttpServletResponse response, Locale locale);

    Locale retrieveLocale(HttpServletRequest request);
}
