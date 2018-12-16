package com.epam.hrynyshyn.controllers.captcha.service.entity;

/**
 * Captcha data container.
 */
public class Captcha {
    private String id;
    private String code;
    private long expirationTime;

    public Captcha(String id, String code, long expirationTime) {
        this.id = id;
        this.code = code;
        this.expirationTime = expirationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public boolean isAlive() {
        return System.currentTimeMillis() < expirationTime;
    }
}
