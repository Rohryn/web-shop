package com.epam.hrynyshyn.controllers.login;

import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import com.epam.hrynyshyn.model.services.user.DefaultUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.EMAIL_NOT_EXISTS;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.PASSWORD_NOT_CORRECT;
import static com.epam.hrynyshyn.constants.Constants.Services.USER_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.User.SOURCE_PAGE_URL;
import static com.epam.hrynyshyn.constants.Constants.User.USER;
import static com.epam.hrynyshyn.constants.Constants.User.USER_AVATAR;
import static com.epam.hrynyshyn.constants.Constants.User.USER_NAME;
import static com.epam.hrynyshyn.constants.Constants.Validation.AVATARS_EXTENSION;
import static com.epam.hrynyshyn.constants.Constants.Validation.AVATARS_STORAGE_PATH;
import static com.epam.hrynyshyn.constants.Constants.Validation.ERRORS;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    private DefaultUserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("in get");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("in post");
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);

        logger.info(email + " " + password);
        Map<String, String> errors = validateInputData(email, password);

        HttpSession session = req.getSession();
        session.setAttribute(EMAIL, email);

        if (errors.size() != 0) {
            session.setAttribute(ERRORS, errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        } else {
            session.removeAttribute(ERRORS);
            session.setAttribute(USER, service.getUser(email));
            session.setAttribute(USER_NAME, service.getFullName(email));
            session.setAttribute(USER_AVATAR,
                    AVATARS_STORAGE_PATH + email + AVATARS_EXTENSION);
            String source_url = (String) session.getAttribute(SOURCE_PAGE_URL);
            if (source_url != null) {
                session.removeAttribute(SOURCE_PAGE_URL);
                resp.sendRedirect(source_url);
            } else {
                resp.sendRedirect(req.getHeader("referer"));
            }
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (DefaultUserService) config.getServletContext().getAttribute(USER_SERVICE);
    }

    private Map<String, String> validateInputData(String email, String password) throws TransactionFailureException {
        Map<String, String> errors = new HashMap<>();
        if (!service.emailReserved(email)) {
            errors.put(EMAIL, EMAIL_NOT_EXISTS);
        } else if (!service.passwordCorrect(email, password)) {
            errors.put(PASSWORD, PASSWORD_NOT_CORRECT);
        }
        return errors;
    }
}
