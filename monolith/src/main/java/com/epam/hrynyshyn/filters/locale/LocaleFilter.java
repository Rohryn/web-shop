package com.epam.hrynyshyn.filters.locale;

import com.epam.hrynyshyn.exceptions.ConfigurationFailureException;
import com.epam.hrynyshyn.filters.locale.service.CookieLocaleStorage;
import com.epam.hrynyshyn.filters.locale.service.LocaleStorage;
import com.epam.hrynyshyn.filters.locale.service.SessionLocaleStorage;
import com.epam.hrynyshyn.filters.locale.sources.BrowserLocaleSource;
import com.epam.hrynyshyn.filters.locale.sources.DefaultLocaleSource;
import com.epam.hrynyshyn.filters.locale.sources.LocaleSource;
import com.epam.hrynyshyn.filters.locale.sources.RequestLocaleSource;
import com.epam.hrynyshyn.filters.locale.sources.StorageLocalSource;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.INCORRECT_LOCALE_STORAGE_MODE;
import static com.epam.hrynyshyn.constants.Constants.Locale.COOKIE_LOCALE_STORAGE;
import static com.epam.hrynyshyn.constants.Constants.Locale.LOCALES;
import static com.epam.hrynyshyn.constants.Constants.Locale.LOCALE_STORAGE_MODE;
import static com.epam.hrynyshyn.constants.Constants.Locale.SESSION_LOCALE_STORAGE;

public class LocaleFilter implements Filter {
    protected LocaleStorage localeStorage;
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        setUpLocaleStorage(config);
    }

    protected void setUpLocaleStorage(FilterConfig config) {
        Map<String, LocaleStorage> storages = new HashMap<>();
        storages.put(SESSION_LOCALE_STORAGE, new SessionLocaleStorage());
        storages.put(COOKIE_LOCALE_STORAGE, new CookieLocaleStorage());
        localeStorage = storages.get(config.getInitParameter(LOCALE_STORAGE_MODE));
        if (localeStorage == null) {
            throw new ConfigurationFailureException(INCORRECT_LOCALE_STORAGE_MODE);
        }
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        List<LocaleSource> localeSources = getLocalSources(request);
        Locale locale = null;
        for (LocaleSource localeSource : localeSources) {
            locale = localeSource.getLocale();
            if (isLocaleObtained(locale)) {
                break;
            }
        }
        localeStorage.store(request, response, locale);
        filterChain.doFilter(getLocaleSpecifiedRequest(request, locale), servletResponse);
    }

    protected List<LocaleSource> getLocalSources(HttpServletRequest request) {
        List<LocaleSource> localeSources = new ArrayList<>();
        localeSources.add(new RequestLocaleSource(request));
        localeSources.add(new StorageLocalSource(request, localeStorage));
        localeSources.add(new BrowserLocaleSource(request, getSupportedLocales(config)));
        localeSources.add(new DefaultLocaleSource(config));
        return localeSources;
    }

    protected boolean isLocaleObtained(Locale locale) {
        return locale != null;
    }

    protected List<Locale> getSupportedLocales(FilterConfig config) {
        List<Locale> locales = new ArrayList<>();
        String localesFromConfig = config.getInitParameter(LOCALES);
        String[] supportedLocales = localesFromConfig.split(",");
        for (String supportedLocale : supportedLocales) {
            locales.add(new Locale(supportedLocale));
        }
        return locales;
    }

    protected HttpServletRequest getLocaleSpecifiedRequest(HttpServletRequest request, Locale locale) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public Locale getLocale() {
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                Set<Locale> locales = new HashSet<>();
                locales.add(locale);
                return Collections.enumeration(locales);
            }
        };
    }

    @Override
    public void destroy() {

    }
}
