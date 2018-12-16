package com.epam.hrynyshyn.controllers.registration;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class RegistrationBeanValidatorTest {
    private RegistrationFormBean registrationFormBean;
    private int expectedErrorsNumber;
    private RegistrationBeanValidator validator;

    public RegistrationBeanValidatorTest(RegistrationFormBean registrationFormBean, int expectedErrorsNumber) {
        this.registrationFormBean = registrationFormBean;
        this.expectedErrorsNumber = expectedErrorsNumber;

    }

    @Parameterized.Parameters
    public static Collection registrationForms() {
        return Arrays.asList(new Object[][]{
                {new RegistrationFormBean("Ivan",
                        "Ivanov",
                        "correct@email.com",
                        "123456789",
                        "123456789"), 0},
                {new RegistrationFormBean("Ivan",
                        "Ivanov",
                        "not correct@email.com",
                        "123456789",
                        "123456789"), 1},
                {new RegistrationFormBean("Ivan",
                        "Ivanov",
                        "correct@email.com",
                        "123456789",
                        "1234567"), 1},
                {new RegistrationFormBean("Ivan",
                        "Ivanov",
                        "correct@email.com",
                        "123",
                        "123"), 1},
                {new RegistrationFormBean("",
                        "",
                        "",
                        "",
                        ""), 4}
        });
    }

    @Test
    public void testValidate() throws Exception {
        assertEquals(expectedErrorsNumber,validator.validateBean(registrationFormBean).size());
    }

}