package com.epam.hrynyshyn.filters.locale.sources;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static com.epam.hrynyshyn.constants.Constants.Locale.LANG;

public class RequestLocaleSource implements LocaleSource {
    private HttpServletRequest request;

    public RequestLocaleSource(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Locale getLocale() {
        String localeCode = request.getParameter(LANG);
        return localeCode == null ? null : new Locale(localeCode);
    }
}
