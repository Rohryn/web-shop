package com.epam.hrynyshyn.model.services.product;

import com.epam.hrynyshyn.model.persistense.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product getProductById(int productId);

    int getProductsCountBySelectParameters(Map<String, Object> parameters);

    List<Product> getProductsByQueryParameters(Map<String, Object> parameters);
}
