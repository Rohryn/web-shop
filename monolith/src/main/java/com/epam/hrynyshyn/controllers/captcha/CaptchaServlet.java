package com.epam.hrynyshyn.controllers.captcha;


import com.epam.hrynyshyn.controllers.captcha.service.providers.CaptchaProvider;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_PROVIDER;
import static com.epam.hrynyshyn.constants.Constants.Validation.REGISTRATION_TIMEOUT;


/**
 * This servlet invokes during registration.jsp rendering process.
 * It's responsible for setting registration timeout and generating captcha.
 */
@WebServlet("/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    private CaptchaProvider provider;

    private void setRegistrationTimeout(HttpServletRequest request) {
        int registrationTimeout = Integer.parseInt(request.getServletContext().getInitParameter(REGISTRATION_TIMEOUT));
        long registrationExpireTime = System.currentTimeMillis() + registrationTimeout;
        request.getSession().setAttribute(REGISTRATION_TIMEOUT, registrationExpireTime);
    }

    private void sendCaptchaToUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage captcha = provider.createCaptcha(request, response);
        response.setContentType("image/png");
        try (OutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(captcha, "png", outputStream);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        provider = (CaptchaProvider) config.getServletContext().getAttribute(CAPTCHA_PROVIDER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setRegistrationTimeout(request);
        sendCaptchaToUser(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
