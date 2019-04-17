package com.epam.hrynyshyn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
@Controller
public class ApplicationPageController {

    @GetMapping("/shoppingCart.do")
    public String getShoppingCartPage() {
        return "cart";
    }

    @GetMapping("/order/order.do")
    public String getOrderPage() {
        return "order";
    }

    @GetMapping("/order/order-complete.do")
    public String getOrderCompletePage() {
        return "order-complete";
    }

    @GetMapping("/accessDenied.do")
    public String getAccessDeniedPage() {
        return "access_denied";
    }

    @GetMapping("/login.do")
    public String getLoginPage() {
        return "login";
    }
}
