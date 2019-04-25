package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.order.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer> {
    // TODO: 2019-04-17 refactor
    void addProduct(OrderedProduct product);
}
