package com.epam.hrynyshyn.tags;

import java.util.Arrays;

public class ParametersContains {
    public static boolean contains(String[] parameters, Object item) {
        return parameters != null && Arrays.asList(parameters).contains(item);
    }
}
