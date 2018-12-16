package com.epam.hrynyshyn.controllers.order;

import com.epam.hrynyshyn.model.persistense.entity.Product;
import com.epam.hrynyshyn.model.persistense.order.storage.ShoppingCart;

import javax.servlet.annotation.WebServlet;

@WebServlet("/incrementQuantity.do")
public class IncrementQuantityServlet extends ChangeCountAbstractServlet {

    @Override
    protected void doActionWithCart(ShoppingCart cart, Product product) {
        cart.addProduct(product);
    }
}
