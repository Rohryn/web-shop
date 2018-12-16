package com.epam.hrynyshyn.filters.locale.sources;

import javax.servlet.FilterConfig;
import java.util.Locale;

import static com.epam.hrynyshyn.constants.Constants.Locale.DEFAULT_LOCALE;

public class DefaultLocaleSource implements LocaleSource {
    private FilterConfig config;

    public DefaultLocaleSource(FilterConfig config) {
        this.config = config;
    }

    @Override
    public Locale getLocale() {
        return new Locale(config.getInitParameter(DEFAULT_LOCALE));
    }
}
