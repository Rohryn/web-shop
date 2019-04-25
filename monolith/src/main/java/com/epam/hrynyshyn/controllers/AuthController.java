package com.epam.hrynyshyn.controllers;

import com.epam.hrynyshyn.controllers.login.LoginServlet;
import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import com.epam.hrynyshyn.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.EMAIL_NOT_EXISTS;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.PASSWORD_NOT_CORRECT;
import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.IMAGE_MIME_TYPE;
import static com.epam.hrynyshyn.constants.Constants.User.PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.User.SOURCE_PAGE_URL;
import static com.epam.hrynyshyn.constants.Constants.User.USER;
import static com.epam.hrynyshyn.constants.Constants.User.USER_AVATAR;
import static com.epam.hrynyshyn.constants.Constants.User.USER_NAME;
import static com.epam.hrynyshyn.constants.Constants.Validation.AVATARS_EXTENSION;
import static com.epam.hrynyshyn.constants.Constants.Validation.AVATARS_STORAGE_PATH;
import static com.epam.hrynyshyn.constants.Constants.Validation.ERRORS;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-16.
 */
@Controller
public class AuthController {
    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    private UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login.do")
    public String executeLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpServletRequest req,
                               HttpServletResponse resp) throws IOException {
        LOG.info("in post");

        LOG.info(email + " " + password);
        Map<String, String> errors = validateInputData(email, password);

        HttpSession session = req.getSession();
        session.setAttribute(EMAIL, email);

        if (errors.size() != 0) {
            session.setAttribute(ERRORS, errors);
            return "forward:login";
        } else {
            session.removeAttribute(ERRORS);
            session.setAttribute(USER, service.get(email));
            session.setAttribute(USER_NAME, service.getFullName(email));
            session.setAttribute(USER_AVATAR,
                    AVATARS_STORAGE_PATH + email + AVATARS_EXTENSION);
            String source_url = (String) session.getAttribute(SOURCE_PAGE_URL);
            if (source_url != null) {
                session.removeAttribute(SOURCE_PAGE_URL);
                resp.sendRedirect(source_url);
                // TODO: 2019-04-17 return redirect page
                return null;
            } else {
                resp.sendRedirect(req.getHeader("referer"));
                // TODO: 2019-04-17 return referer page
                return null;
            }
        }
    }

    @GetMapping("/logout.do")
    public void executeLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute(USER_NAME);
        resp.sendRedirect(req.getHeader("referer"));
    }

    // TODO: 2019-04-17 refactor
    @GetMapping("/receiveAvatar")
    public void getAvatar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = (String) req.getSession().getAttribute(EMAIL);
        File avatar = new File(AVATARS_STORAGE_PATH + email + AVATARS_EXTENSION);
        sendAvatar(avatar, resp);
    }

    private void sendAvatar(File avatar, HttpServletResponse response) throws IOException {
        response.setContentType(IMAGE_MIME_TYPE);
        try (OutputStream outputStream = response.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(avatar)) {
            byte[] buf = new byte[1024];
            int count;
            while ((count = fileInputStream.read(buf)) >= 0) {
                outputStream.write(buf, 0, count);
            }
        }
    }

    private Map<String, String> validateInputData(String email, String password) throws TransactionFailureException {
        Map<String, String> errors = new HashMap<>();
        if (!service.isEmailReserved(email)) {
            errors.put(EMAIL, EMAIL_NOT_EXISTS);
        } else if (!service.isPasswordCorrect(email, password)) {
            errors.put(PASSWORD, PASSWORD_NOT_CORRECT);
        }
        return errors;
    }
}
