package com.epam.hrynyshyn.controllers.registration;

import com.epam.hrynyshyn.model.persistense.entity.User;

import javax.servlet.http.HttpServletRequest;

import static com.epam.hrynyshyn.constants.Constants.User.*;
import static com.epam.hrynyshyn.constants.Constants.Validation.*;

/**
 * Container for data from registration form.
 */
public class RegistrationFormBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public void fillUserData(User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
    }

    public static RegistrationFormBean createBean(HttpServletRequest request) {
        RegistrationFormBean bean = new RegistrationFormBean();
        bean.setFirstName(request.getParameter(FIRST_NAME));
        bean.setLastName(request.getParameter(LAST_NAME));
        bean.setEmail(request.getParameter(EMAIL));
        bean.setPassword(request.getParameter(PASSWORD));
        bean.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
        return bean;
    }

    public RegistrationFormBean() {
    }

    public RegistrationFormBean(String firstName, String lastName, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
