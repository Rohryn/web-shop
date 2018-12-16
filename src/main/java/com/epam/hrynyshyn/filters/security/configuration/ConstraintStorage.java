package com.epam.hrynyshyn.filters.security.configuration;


import java.util.List;

public final class ConstraintStorage {
    private List<SecurityConstraint> constraints;

    public ConstraintStorage(List<SecurityConstraint> constraints) {
        this.constraints = constraints;
    }

    public List<SecurityConstraint> getConstraints() {
        return constraints;
    }
}
