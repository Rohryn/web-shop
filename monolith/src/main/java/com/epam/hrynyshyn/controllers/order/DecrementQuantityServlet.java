package com.epam.hrynyshyn.controllers.order;

import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.model.ShoppingCart;

import javax.servlet.annotation.WebServlet;
@Deprecated
@WebServlet("/decrementQuantity.do")
public class DecrementQuantityServlet extends ChangeCountAbstractServlet {

    @Override
    protected void doActionWithCart(ShoppingCart cart, Product product) {
        cart.removeOneProduct(product);
    }
}
