package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Map;


public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Product findById(int productId);

    @Deprecated
    int getProductsCountBySelectParameters(Map<String, Object> parameters);

    int getProductsCountBySpecification(Specification<Integer> specification);

    @Deprecated
    List<Product> getProductsBySelectParameters(Map<String, Object> parameters);

    List<Product> getProductsBySpecification(Specification<Product> specification);

}
