package com.epam.hrynyshyn.exceptions;

public class ConfigurationFailureException extends RuntimeException {
    public ConfigurationFailureException(String message) {
        super(message);
    }

    public ConfigurationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
