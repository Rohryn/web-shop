package com.epam.hrynyshyn.filters.locale.sources;

import com.epam.hrynyshyn.filters.locale.service.LocaleStorage;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class StorageLocalSource implements LocaleSource {
    private HttpServletRequest request;
    private LocaleStorage storage;

    public StorageLocalSource(HttpServletRequest request, LocaleStorage storage) {
        this.request = request;
        this.storage = storage;
    }

    @Override
    public Locale getLocale() {
        return storage.retrieveLocale(request);
    }
}
