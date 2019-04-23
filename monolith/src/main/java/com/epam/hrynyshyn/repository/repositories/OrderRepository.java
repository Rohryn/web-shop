package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
