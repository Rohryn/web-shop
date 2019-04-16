package com.epam.hrynyshyn.controllers.captcha.service.providers;

import com.epam.hrynyshyn.controllers.captcha.service.CaptchaService;
import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_ID;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_LENGTH;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_SYMBOLS;
import static com.epam.hrynyshyn.constants.Constants.Captcha.CAPTCHA_TIMEOUT;

/**
 * Main logic of captcha support.
 */
public abstract class AbstractCaptchaProvider implements CaptchaProvider {
    protected CaptchaService service;
    protected Logger logger = Logger.getLogger(this.getClass());

    private BufferedImage renderCaptcha(String captchaCode) {
        int width = 150;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        GradientPaint gp = new GradientPaint(0, 0, Color.red, 0, height / 2, Color.black, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(255, 153, 0));

        int x = 0;
        int y = 0;
        char[] captchaChars = captchaCode.toCharArray();
        Random r = new Random();
        for (int i = 0; i < captchaChars.length; i++) {
            x += 10 + (Math.abs(r.nextInt()) % 15);
            y = 20 + Math.abs(r.nextInt()) % 20;
            g2d.drawChars(captchaChars, i, 1, x, y);
        }
        g2d.dispose();
        return bufferedImage;
    }

    private String generateCaptchaCode() {
        List<Character> characters = Arrays.asList(CAPTCHA_SYMBOLS);
        Collections.shuffle(characters);
        StringBuilder builder = new StringBuilder();
        for (Character character : characters) {
            builder.append(character);
        }
        return builder.toString().substring(0, CAPTCHA_LENGTH);
    }

    private boolean isCaptchaIdValid(String id) {
        return id != null && id.length() != 0;
    }

    protected abstract void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    @Override
    public BufferedImage createCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captchaId = request.getParameter(CAPTCHA_ID);

        logger.info("captcha id " + captchaId);

        if (isCaptchaIdValid(captchaId)) {
            String captchaCode = generateCaptchaCode();
            logger.info("captcha code" + captchaCode);
            long captchaTimeout = System.currentTimeMillis() + Long.parseLong(request.getServletContext().getInitParameter(CAPTCHA_TIMEOUT));
            Captcha captcha = new Captcha(captchaId, captchaCode, captchaTimeout);
            BufferedImage captchaImage = renderCaptcha(captchaCode);
            logger.info("captcha creation");
            service.addCaptcha(captcha);
            saveCaptcha(request, response, captcha);
            return captchaImage;
        } else {
            logger.info("Invalid captcha id");
        }
        return null;
    }

    public AbstractCaptchaProvider(CaptchaService service) {
        this.service = service;
    }

    @Override
    public void removeExpiredCaptcha() {
        service.removeExpiredCaptcha();
    }
}
