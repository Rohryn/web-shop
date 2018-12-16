package com.epam.hrynyshyn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation methods container.
 */
public class Validators {

    public static boolean isEmailValid(String email) {
        String pattern = "\\S+@\\S+\\.\\S+";
        return isByPatternValid(email, pattern);
    }

    public static boolean isPasswordValid(String password) {
        String pattern = "\\S{8,}";
        return isByPatternValid(password, pattern);
    }

    private static boolean isByPatternValid(String value, String pattern) {
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(value);
        return matcher.matches();
    }
}
