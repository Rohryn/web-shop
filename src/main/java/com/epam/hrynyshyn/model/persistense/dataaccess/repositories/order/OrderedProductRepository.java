package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.order;

import com.epam.hrynyshyn.model.persistense.order.product.OrderedProduct;

public interface OrderedProductRepository {

    void addProduct(OrderedProduct product);
}
