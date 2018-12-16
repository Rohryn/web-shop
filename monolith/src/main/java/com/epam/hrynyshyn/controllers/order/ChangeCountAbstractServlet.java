package com.epam.hrynyshyn.controllers.order;


import com.epam.hrynyshyn.model.persistense.entity.Product;
import com.epam.hrynyshyn.model.persistense.order.storage.ShoppingCart;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.Order.*;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_COUNT;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_ID;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_SUM;
import static com.epam.hrynyshyn.constants.Constants.Order.TOTAL_SUM;
import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;

public abstract class ChangeCountAbstractServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        int id = Integer.parseInt(req.getParameter(PRODUCT_ID));
        Product product = cart.getProductById(id);
        doActionWithCart(cart, product);
        JSONObject json = prepareJSON(cart, id);
        sendResponse(resp, json);
    }

    private JSONObject prepareJSON(ShoppingCart cart, int productId) {
        Product product = cart.getProductById(productId);
        JSONObject json = new JSONObject();
        json.put(PRODUCT_COUNT, cart.getProductCount(product));
        json.put(PRODUCT_SUM, cart.getSumForProduct(product));
        json.put(TOTAL_SUM, cart.getTotalSum());
        return json;
    }

    private void sendResponse(HttpServletResponse response, JSONObject json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().print(json.toString());
    }

    protected abstract void doActionWithCart(ShoppingCart cart, Product product);
}
