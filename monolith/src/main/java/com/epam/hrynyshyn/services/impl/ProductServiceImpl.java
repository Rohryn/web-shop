package com.epam.hrynyshyn.services.impl;

import com.epam.hrynyshyn.repository.repositories.ProductRepository;
import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProductById(int productId) {
        return repository.getProductById(productId);
    }

    @Override
    public int getProductsCountBySelectParameters(Map<String, Object> parameters) {
        return repository.getProductsCountBySelectParameters(parameters);
    }

    @Override
    public List<Product> getProductsByQueryParameters(Map<String, Object> parameters) {
        return repository.getProductsBySelectParameters(parameters);
    }
}
