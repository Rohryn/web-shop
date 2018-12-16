package com.epam.hrynyshyn.controllers.captcha.service.providers;

import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * General interface for captcha providers.
 */
public interface CaptchaProvider {
    /**
     * Creates captcha image and stores it in response.
     *
     * @param request  contains necessary data from client-side.
     * @param response image with code stores here.
     * @return {@code BufferedImage} instance of created captcha image.
     * @throws IOException
     */
    BufferedImage createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Extracts captcha from storage with necessary data stored in  request.
     *
     * @param request source of necessary data.
     * @return {@code Captcha} instance.
     */
    Captcha getCaptchaFromStorage(HttpServletRequest request);

    /**
     * Captcha storage cleaning process. Removes all expired captcha.
     */
    void removeExpiredCaptcha();


}
