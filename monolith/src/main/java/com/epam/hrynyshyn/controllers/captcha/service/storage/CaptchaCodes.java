package com.epam.hrynyshyn.controllers.captcha.service.storage;

import com.epam.hrynyshyn.controllers.captcha.service.entity.Captcha;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple storage for captcha
 */
public class CaptchaCodes {
    private Map<String, Captcha> captchaStore;

    public CaptchaCodes() {
        captchaStore = new ConcurrentHashMap<>();
    }

    public Captcha getCaptcha(String captchaId) {
        return captchaStore.get(captchaId);
    }

    public void addCaptcha(Captcha captcha) {
        captchaStore.put(captcha.getId(), captcha);
    }

    public void removeExpiredCaptcha() {
        for (Captcha captcha : captchaStore.values()) {
            if (!captcha.isAlive()) {
                captchaStore.remove(captcha.getId());
            }
        }
    }
}
