package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.order.OrderedProduct;
import org.springframework.data.repository.Repository;

public interface OrderedProductRepository extends Repository<OrderedProduct, Integer> {
    // TODO: 2019-04-17 refactor
    void addProduct(OrderedProduct product);
}
