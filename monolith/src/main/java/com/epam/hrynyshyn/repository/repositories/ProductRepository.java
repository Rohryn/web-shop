package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Product;

import java.util.List;
import java.util.Map;


public interface ProductRepository {
    Product getProductById(int productId);

    int getProductsCountBySelectParameters(Map<String, Object> parameters);

    List<Product> getProductsBySelectParameters(Map<String, Object> parameters);

}
