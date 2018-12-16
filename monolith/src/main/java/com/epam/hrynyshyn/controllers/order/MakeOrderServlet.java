package com.epam.hrynyshyn.controllers.order;

import com.epam.hrynyshyn.model.persistense.entity.Product;
import com.epam.hrynyshyn.model.persistense.entity.User;
import com.epam.hrynyshyn.model.persistense.order.Order;
import com.epam.hrynyshyn.model.persistense.order.product.OrderedProduct;
import com.epam.hrynyshyn.model.persistense.order.storage.ShoppingCart;
import com.epam.hrynyshyn.model.services.order.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Order.*;
import static com.epam.hrynyshyn.constants.Constants.Order.ORDER;
import static com.epam.hrynyshyn.constants.Constants.Order.ORDER_CREATED;
import static com.epam.hrynyshyn.constants.Constants.Order.ORDER_CREATED_DESCRIPTION;
import static com.epam.hrynyshyn.constants.Constants.Order.PAYMENT_CARD;
import static com.epam.hrynyshyn.constants.Constants.Order.SHIPMENT_ADDRESS;
import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;
import static com.epam.hrynyshyn.constants.Constants.Services.ORDER_SERVICE;
import static com.epam.hrynyshyn.constants.Constants.User.USER;

@WebServlet("/order/makeOrder.do")
public class MakeOrderServlet extends HttpServlet {
    private OrderService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        service = (OrderService) context.getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/order/order.do").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        Order order = createOrder(req);
        addProductsToOrder(order, cart);
        service.addOrder(order);
        cart.clear();
        req.setAttribute(ORDER, order);
        req.getRequestDispatcher("/order/order_complete.do").forward(req, resp);
    }

    private Order createOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String address = request.getParameter(SHIPMENT_ADDRESS);
        String paymentCard = request.getParameter(PAYMENT_CARD);
        User user = (User) session.getAttribute(USER);
        Order order = new Order();
        order.setCustomer(user);
        order.setStatus(ORDER_CREATED);
        order.setStatusDescription(ORDER_CREATED_DESCRIPTION);
        order.setShipmentAddress(address);
        order.setPaymentCard(paymentCard);
        return order;
    }

    private void addProductsToOrder(Order order, ShoppingCart cart) {
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        List<Product> products = cart.getProducts();
        for (Product product : products) {
            OrderedProduct orderedProduct = new OrderedProduct(product, cart.getProductCount(product));
            orderedProducts.add(orderedProduct);
        }
        order.setProducts(orderedProducts);
    }
}
