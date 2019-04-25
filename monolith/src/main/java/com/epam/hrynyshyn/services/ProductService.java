package com.epam.hrynyshyn.services;

import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.model.request.ProductRequest;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product getProductById(int productId);

    int getProductsCountBySelectParameters(Map<String, Object> parameters);

    long getProductsCount(ProductRequest request);

    List<Product> getProductsByQueryParameters(Map<String, Object> parameters);

    List<Product> getProducts(ProductRequest request);
}
