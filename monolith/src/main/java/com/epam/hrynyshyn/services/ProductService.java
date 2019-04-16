package com.epam.hrynyshyn.services;

import com.epam.hrynyshyn.model.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product getProductById(int productId);

    int getProductsCountBySelectParameters(Map<String, Object> parameters);

    List<Product> getProductsByQueryParameters(Map<String, Object> parameters);
}
