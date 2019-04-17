package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Product;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Map;


public interface ProductRepository extends Repository<Product,Integer> {
    Product getProductById(int productId);

    int getProductsCountBySelectParameters(Map<String, Object> parameters);

    List<Product> getProductsBySelectParameters(Map<String, Object> parameters);

}
