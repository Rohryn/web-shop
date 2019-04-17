package com.epam.hrynyshyn.listeners;

import com.epam.hrynyshyn.model.ShoppingCart;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;

//@WebListener
public class SessionListener /*implements HttpSessionListener*/ {
//    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(SHOPPING_CART, cart);
    }

//    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
