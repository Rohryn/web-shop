package com.epam.hrynyshyn.filters.security.configuration;


import java.util.List;

public class SecurityConstraint {
    private String urlPatter;
    private List<String> roles;

    public String getUrlPattern() {
        return urlPatter;
    }

    public void setUrlPatter(String urlPatter) {
        this.urlPatter = urlPatter;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
