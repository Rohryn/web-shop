package com.epam.hrynyshyn.util;


public class RequestParametersValidator {
    public static boolean isParameterExists(Object parameter) {
        return parameter != null;
    }

    public static boolean isArrayValid(Object[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isPositiveNumber(String value) {
        try {
            int number = Integer.parseInt(value);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
