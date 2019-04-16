package com.epam.hrynyshyn.controllers.captcha.service;

import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;

public interface CaptchaService {

    Captcha getCaptcha(String id);

    void addCaptcha(Captcha captcha);

    void removeExpiredCaptcha();
}
