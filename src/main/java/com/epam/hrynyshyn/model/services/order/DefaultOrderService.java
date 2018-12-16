package com.epam.hrynyshyn.model.services.order;

import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.order.OrderRepository;
import com.epam.hrynyshyn.model.persistense.order.Order;

public class DefaultOrderService implements OrderService {
    private OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);

    }
}
