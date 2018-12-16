package com.epam.hrynyshyn.controllers.order;

import com.epam.hrynyshyn.model.persistense.entity.Product;
import com.epam.hrynyshyn.model.persistense.order.storage.ShoppingCart;
import com.epam.hrynyshyn.model.services.product.ProductService;
import org.json.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_ID;
import static com.epam.hrynyshyn.constants.Constants.Order.TOTAL_SUM;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCT_COUNT;
import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;
import static com.epam.hrynyshyn.constants.Constants.Services.PRODUCT_SERVICE;

@WebServlet("/addToCart.do")
public class AddProductToCartServlet extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter(PRODUCT_ID));
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute(SHOPPING_CART);
        addProduct(cart, productId);
        sendResponse(req, resp);
    }

    private void addProduct(ShoppingCart cart, int productId) {
        Product product = productService.getProductById(productId);
        cart.addProduct(product);
    }

    private void sendResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        JSONObject json = getJSONCart(cart);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().print(json.toString());
    }

    private JSONObject getJSONCart(ShoppingCart cart) {
        JSONObject json = new JSONObject();
        json.put(PRODUCT_COUNT, cart.getProductsCount());
        json.put(TOTAL_SUM, cart.getTotalSum());
        return json;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
    }
}
