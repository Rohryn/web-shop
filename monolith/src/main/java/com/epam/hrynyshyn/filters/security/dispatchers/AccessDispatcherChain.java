package com.epam.hrynyshyn.filters.security.dispatchers;

import com.epam.hrynyshyn.filters.security.configuration.ConstraintStorage;

public class AccessDispatcherChain {

    private ConstraintStorage constraintStorage;

    public AccessDispatcherChain(ConstraintStorage constraintStorage) {
        this.constraintStorage = constraintStorage;
    }

    public AbstractAccessDispatcher constructDispatcherChain() {
        AbstractAccessDispatcher roleAccessDispatcher = new RoleAccessDispatcher(constraintStorage);
        AbstractAccessDispatcher userAccessDispatcher = new UserAccessDispatcher(constraintStorage);
        AbstractAccessDispatcher pageAccessDispatcher = new PageAccessDispatcher(constraintStorage);
        pageAccessDispatcher.setNextDispatcher(userAccessDispatcher);
        userAccessDispatcher.setNextDispatcher(roleAccessDispatcher);
        return pageAccessDispatcher;
    }
}
