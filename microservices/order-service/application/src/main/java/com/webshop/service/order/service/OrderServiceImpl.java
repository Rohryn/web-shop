package com.webshop.service.order.service;

import com.epam.hrynyshyn.model.order.Order;
import com.epam.hrynyshyn.repository.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.save(order);

    }
}
