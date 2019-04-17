package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.order.Order;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order,Integer> {
    // TODO: 2019-04-17 refactor
    void addOrder(Order order);
}
