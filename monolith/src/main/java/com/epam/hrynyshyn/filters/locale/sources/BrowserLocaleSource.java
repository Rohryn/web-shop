package com.epam.hrynyshyn.filters.locale.sources;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class BrowserLocaleSource implements LocaleSource {
    private HttpServletRequest request;
    private List<Locale> supportedLocales;

    public BrowserLocaleSource(HttpServletRequest request, List<Locale> supportedLocales) {
        this.request = request;
        this.supportedLocales = supportedLocales;
    }

    @Override
    public Locale getLocale() {
        List<Locale> localesFromBrowser = Collections.list(request.getLocales());
        for (Locale locale : localesFromBrowser) {
            if (supportedLocales.contains(locale)) {
                return locale;
            }
        }
        return null;
    }
}
