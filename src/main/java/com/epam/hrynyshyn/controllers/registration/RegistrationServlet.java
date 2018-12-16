package com.epam.hrynyshyn.controllers.registration;

import com.epam.hrynyshyn.controllers.captcha.service.providers.CaptchaProvider;
import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import com.epam.hrynyshyn.model.persistense.entity.User;
import com.epam.hrynyshyn.model.services.user.DefaultUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA;
import static com.epam.hrynyshyn.constants.Constants.Captcha.*;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.INCORRECT_CAPTCHA;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.RESERVED_EMAIL;
import static com.epam.hrynyshyn.constants.Constants.Services.USER_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.Validation.*;

/**
 * User registration process.
 */
@WebServlet("/registration.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegistrationServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    private DefaultUserService service;
    private CaptchaProvider provider;

    private boolean isRegistrationOutOfTime(long timeOut) {
        return System.currentTimeMillis() > timeOut;
    }

    private boolean isEmailReserved(String email) throws TransactionFailureException {
        return service.emailReserved(email);
    }

    private boolean isCaptchaCorrect(HttpServletRequest request) {
        String expectedCaptcha = provider.getCaptchaFromStorage(request).getCode();
        String inputtedCaptcha = request.getParameter(CAPTCHA);
        logger.info("expected: " + expectedCaptcha);
        logger.info("got: " + inputtedCaptcha);
        return inputtedCaptcha != null && inputtedCaptcha.equals(expectedCaptcha);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Generating id");
        String captchaId = UUID.randomUUID().toString();
        req.setAttribute(CAPTCHA_ID, captchaId);
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (isRegistrationOutOfTime((long) session.getAttribute(REGISTRATION_TIMEOUT))) {
            resp.sendRedirect("registrationTimeout.html");
            return;
        }

        RegistrationFormBean formBean = RegistrationFormBean.createBean(req);
        Map<String, String> errors = RegistrationBeanValidator.validateBean(formBean);

        try {
            if (isEmailReserved(formBean.getEmail())) {
                errors.put(EMAIL, RESERVED_EMAIL);
            }
            if (!isCaptchaCorrect(req)) {
                errors.put(CAPTCHA, INCORRECT_CAPTCHA);
            }
            if (errors.size() != 0) {
                formBean.setPassword("");
                formBean.setConfirmPassword("");
                session.setAttribute(FORM_DATA, formBean);
                session.setAttribute(ERRORS, errors);
                resp.sendRedirect("registration");
            } else {
                User user = new User();
                formBean.fillUserData(user);
                service.addUser(user);
                FileUploader.uploadAvatar(req);
                resp.sendRedirect("/WEB-INF/index.jsp");
            }
        } catch (TransactionFailureException ex) {
            logger.error("Transaction failure", ex);
            throw new ServletException(ex);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        service = (DefaultUserService) context.getAttribute(USER_SERVICE);
        provider = (CaptchaProvider) context.getAttribute(CAPTCHA_PROVIDER);
    }


}


