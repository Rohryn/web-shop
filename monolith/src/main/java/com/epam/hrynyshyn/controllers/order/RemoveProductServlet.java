package com.epam.hrynyshyn.controllers.order;

import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.model.ShoppingCart;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCT_ID;
import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;

@Deprecated
@WebServlet("/removeProduct.do")
public class RemoveProductServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RemoveProductServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        int id = Integer.parseInt(req.getParameter(PRODUCT_ID));
        Product product = cart.getProductById(id);
        cart.removeProduct(product);
        logger.info("product removed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
