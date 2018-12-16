package com.epam.hrynyshyn.controllers.captcha.service;

import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;
import com.epam.hrynyshyn.controllers.captcha.service.storage.CaptchaCodes;

/**
 * Service for captcha data manipulating.
 */
public class DefaultCaptchaService implements CaptchaService {
    private CaptchaCodes captchaCodes;

    public DefaultCaptchaService(CaptchaCodes captchaCodes) {
        this.captchaCodes = captchaCodes;
    }

    @Override
    public Captcha getCaptcha(String id) {
        return captchaCodes.getCaptcha(id);
    }

    @Override
    public void addCaptcha(Captcha captcha) {
        captchaCodes.addCaptcha(captcha);
    }

    @Override
    public void removeExpiredCaptcha() {
        captchaCodes.removeExpiredCaptcha();
    }

}
