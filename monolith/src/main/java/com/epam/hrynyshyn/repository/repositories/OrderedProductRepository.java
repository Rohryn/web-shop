package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.order.OrderedProduct;

public interface OrderedProductRepository {

    void addProduct(OrderedProduct product);
}
