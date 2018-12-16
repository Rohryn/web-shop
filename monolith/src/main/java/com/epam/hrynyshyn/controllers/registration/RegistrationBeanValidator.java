package com.epam.hrynyshyn.controllers.registration;


import com.epam.hrynyshyn.util.Validators;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.EMPTY_EMAIL;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.EMPTY_LAST_NAME;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.EMPTY_NAME;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.EMPTY_PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.INCORRECT_EMAIL;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.INCORRECT_PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.NOT_MATCH;
import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.FIRST_NAME;
import static com.epam.hrynyshyn.constants.Constants.User.LAST_NAME;
import static com.epam.hrynyshyn.constants.Constants.User.PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.Validation.CONFIRM_PASSWORD;

/**
 * Validator for user's registration form data.
 */
public class RegistrationBeanValidator {
    private static Logger logger = Logger.getLogger(RegistrationBeanValidator.class);

    public static Map<String, String> validateBean(RegistrationFormBean bean) {
        Map<String, String> errors = new HashMap<>();
        String firstName = bean.getFirstName();

        logger.info("firstName " + firstName);

        if (isFieldEmpty(firstName)) {
            errors.put(FIRST_NAME, EMPTY_NAME);
        }

        String lastName = bean.getLastName();
        if (isFieldEmpty(lastName)) {
            errors.put(LAST_NAME, EMPTY_LAST_NAME);
        }

        String email = bean.getEmail();
        if (isFieldEmpty(email)) {
            errors.put(EMAIL, EMPTY_EMAIL);
        } else if (!Validators.isEmailValid(email)) {
            errors.put(EMAIL, INCORRECT_EMAIL);
        }

        String password = bean.getPassword();
        if (isFieldEmpty(password)) {
            errors.put(PASSWORD, EMPTY_PASSWORD);
        } else if (!Validators.isPasswordValid(password)) {
            errors.put(PASSWORD, INCORRECT_PASSWORD);
        }

        String confirmedPassword = bean.getConfirmPassword();
        if (confirmedPassword != null && !confirmedPassword.equals(password)) {
            errors.put(CONFIRM_PASSWORD, NOT_MATCH);
        }
        return errors;
    }

    private static boolean isFieldEmpty(String field) {
        return field == null || field.length() == 0;
    }
}
