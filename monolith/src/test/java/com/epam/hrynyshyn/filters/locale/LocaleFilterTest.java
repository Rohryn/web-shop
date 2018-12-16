package com.epam.hrynyshyn.filters.locale;

import com.epam.hrynyshyn.filters.locale.sources.BrowserLocaleSource;
import com.epam.hrynyshyn.filters.locale.sources.LocaleSource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import static com.epam.hrynyshyn.constants.Constants.Locale.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class LocaleFilterTest {
    private LocaleFilter filter;
    @Mock
    private FilterConfig config;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private FilterChain filterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        configActions();
        filter = new LocaleFilter();
        filter.init(config);

    }

    private void configActions() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(config.getInitParameter(LOCALE_STORAGE_MODE)).thenReturn(SESSION_LOCALE_STORAGE);
        doNothing().when(filterChain).doFilter(request, response);
    }

    @Test
    public void testGetLocaleFromSessionStorageWithNoLocaleInStorage() throws Exception {
        when(config.getInitParameter(LOCALE_STORAGE_MODE)).thenReturn(SESSION_LOCALE_STORAGE);
        createNewFilter();
        when(session.getAttribute(LOCALE)).thenReturn(null);
        Locale locale = filter.localeStorage.retrieveLocale(request);
        assertNull(locale);
    }

    @Test
    public void testGetLocaleFromSessionStorageWithLocaleInStorage() throws Exception {
        Locale expectedLocale = new Locale("en");
        when(config.getInitParameter(LOCALE_STORAGE_MODE)).thenReturn(SESSION_LOCALE_STORAGE);
        createNewFilter();
        when(session.getAttribute(LOCALE)).thenReturn(expectedLocale);
        Locale locale = filter.localeStorage.retrieveLocale(request);
        assertEquals(expectedLocale, locale);
    }

    @Test
    public void testGetLocaleFromCookieStorageWithNoLocaleInStorage() throws Exception {
        when(config.getInitParameter(LOCALE_STORAGE_MODE)).thenReturn(COOKIE_LOCALE_STORAGE);
        createNewFilter();
        when(request.getCookies()).thenReturn(new Cookie[]{});
        Locale locale = filter.localeStorage.retrieveLocale(request);
        assertNull(locale);
    }

    @Test
    public void testGetLocaleFromCookieStorageWithLocaleInStorage() throws Exception {
        Locale expectedLocale = new Locale("en");
        Cookie expectedCookie = new Cookie(LOCALE, "en");
        when(config.getInitParameter(LOCALE_STORAGE_MODE)).thenReturn(COOKIE_LOCALE_STORAGE);
        createNewFilter();
        when(request.getCookies()).thenReturn(new Cookie[]{expectedCookie});
        Locale locale = filter.localeStorage.retrieveLocale(request);
        assertEquals(expectedLocale, locale);
    }

    @Test
    public void testGetLocaleFromBrowserWithNoSupportedLocales() throws Exception {
        Locale[] localesFromBrowser = new Locale[]{new Locale("ar_DZ"), new Locale("zh_CN")};
        when(request.getLocales()).thenReturn(Collections.enumeration(Arrays.asList(localesFromBrowser)));
        when(config.getInitParameter(LOCALES)).thenReturn("en_US,ru_RU");
        LocaleSource source = new BrowserLocaleSource(request, filter.getSupportedLocales(config));
        Locale locale = source.getLocale();
        assertNull(locale);
    }

    @Test
    public void testGetLocaleFromBrowserWithSupportedLocalesAndFirstLocaleCoincidence() throws Exception {
        Locale[] localesFromBrowser = new Locale[]{new Locale("en_US"), new Locale("ru_RU")};
        when(request.getLocales()).thenReturn(Collections.enumeration(Arrays.asList(localesFromBrowser)));
        String supportedLocales = "en_US,ru_RU,zh_CN";
        when(config.getInitParameter(LOCALES)).thenReturn(supportedLocales);
        LocaleSource source = new BrowserLocaleSource(request, filter.getSupportedLocales(config));
        Locale locale = source.getLocale();
        assertEquals(new Locale("en_US"), locale);
    }

    private void createNewFilter() throws Exception {
        filter = new LocaleFilter();
        filter.init(config);
    }


}