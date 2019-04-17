package com.epam.hrynyshyn.services.impl;

import com.epam.hrynyshyn.repository.repositories.OrderRepository;
import com.epam.hrynyshyn.model.order.Order;
import com.epam.hrynyshyn.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);

    }
}
