package com.epam.hrynyshyn.model.services.product;

import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.product.ProductRepository;
import com.epam.hrynyshyn.model.persistense.entity.Product;

import java.util.List;
import java.util.Map;

public class DefaultProductService implements ProductService {
    private ProductRepository repository;

    public DefaultProductService(ProductRepository repository) {
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
