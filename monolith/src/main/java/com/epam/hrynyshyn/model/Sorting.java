package com.epam.hrynyshyn.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
public enum Sorting {
    ASCENDING,
    DESCENDING,
    UNSORTED;

    public static Sorting fromName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (Sorting value : values()) {
            if (name.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return null;
    }
}
