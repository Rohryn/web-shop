package com.webshop.service.order.controller;

import com.epam.hrynyshyn.model.ShoppingCart;
import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.model.entity.User;
import com.epam.hrynyshyn.model.order.Order;
import com.epam.hrynyshyn.model.order.OrderedProduct;
import com.epam.hrynyshyn.services.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Order.ORDER_CREATED;
import static com.epam.hrynyshyn.constants.Constants.Order.ORDER_CREATED_DESCRIPTION;
import static com.epam.hrynyshyn.constants.Constants.Order.PAYMENT_CARD;
import static com.epam.hrynyshyn.constants.Constants.Order.SHIPMENT_ADDRESS;
import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;
import static com.epam.hrynyshyn.constants.Constants.User.USER;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
@RestController("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/makeOrder.do")
    public void makeOrder(@RequestParam(SHIPMENT_ADDRESS) String shipmentAddress,
                          @RequestParam(PAYMENT_CARD) String paymentCard,
                          @SessionAttribute(SHOPPING_CART) ShoppingCart shoppingCart,
                          @SessionAttribute(USER) User user) {
        Order order = new Order();
        order.setCustomer(user);
        order.setStatus(ORDER_CREATED);
        order.setStatusDescription(ORDER_CREATED_DESCRIPTION);
        order.setShipmentAddress(shipmentAddress);
        order.setPaymentCard(paymentCard);
        addProductsToOrder(order, shoppingCart);
        orderService.addOrder(order);
        shoppingCart.clear();
    }

    // TODO: 2019-04-17 refactor, avoid order modification inside
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
