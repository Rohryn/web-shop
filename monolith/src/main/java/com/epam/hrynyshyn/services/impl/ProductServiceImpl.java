package com.epam.hrynyshyn.services.impl;

import com.epam.hrynyshyn.model.Sorting;
import com.epam.hrynyshyn.model.entity.Category;
import com.epam.hrynyshyn.model.entity.Manufacturer;
import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.model.request.ProductRequest;
import com.epam.hrynyshyn.repository.repositories.ProductRepository;
import com.epam.hrynyshyn.services.CategoryService;
import com.epam.hrynyshyn.services.ManufacturerService;
import com.epam.hrynyshyn.services.ProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;
    private ProductRepository repository;

    public ProductServiceImpl(ManufacturerService manufacturerService,
                              CategoryService categoryService,
                              ProductRepository repository) {
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
        this.repository = repository;
    }

    @Override
    public Product getProductById(int productId) {
        return repository.findById(productId);
    }

    @Override
    public int getProductsCountBySelectParameters(Map<String, Object> parameters) {
        return repository.getProductsCountBySelectParameters(parameters);
    }

    @Override
    public long getProductsCount(ProductRequest request) {
        Objects.requireNonNull(request, "Request should be specified");
        Specification<Product> productSpecification = getProductSpecification(request);
        return repository.count(productSpecification);
    }

    @Override
    public List<Product> getProductsByQueryParameters(Map<String, Object> parameters) {
        return repository.getProductsBySelectParameters(parameters);
    }

    @Override
    public List<Product> getProducts(ProductRequest request) {
        Objects.requireNonNull(request, "Request should be specified");
        Specification<Product> productSpecification = getProductSpecification(request);
        PageRequest pageRequest = getPageRequestForProducts(request);
        Page<Product> productsPage = repository.findAll(productSpecification, pageRequest);
        return productsPage.getContent();
    }

    private Specification<Product> getProductSpecification(ProductRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            List<String> manufacturers = request.getManufacturers();
            if (CollectionUtils.isNotEmpty(manufacturers)) {
                List<Integer> manufacturersIds = manufacturerService.getByNames(manufacturers).stream()
                        .map(Manufacturer::getId)
                        .collect(Collectors.toList());
                predicates.add(criteriaBuilder.in(root.get("manufacturer")).in(manufacturersIds));
            }
            List<String> categories = request.getCategories();
            if (CollectionUtils.isNotEmpty(categories)) {
                List<Integer> categoriesIds = categoryService.getByNames(manufacturers).stream()
                        .map(Category::getId)
                        .collect(Collectors.toList());
                predicates.add(criteriaBuilder.in(root.get("category")).in(categoriesIds));
            }
            BigDecimal maximalPrice = request.getMaximalPrice();
            if (Objects.nonNull(maximalPrice)) {
                predicates.add(criteriaBuilder.le(root.get("price"), maximalPrice));
            }
            BigDecimal minimalPrice = request.getMinimalPrice();
            if (Objects.nonNull(minimalPrice)) {
                predicates.add(criteriaBuilder.ge(root.get("price"), minimalPrice));
            }

            if (CollectionUtils.isNotEmpty(predicates)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return null;
        };
    }

    private PageRequest getPageRequestForProducts(ProductRequest request) {
        int pageNumber = request.getPageNumber();
        int productsPerPage = request.getProductsPerPage();
        Sorting sorting = request.getSorting();
        switch (sorting) {
            case UNSORTED:
                return PageRequest.of(pageNumber, productsPerPage);
            case ASCENDING:
                return PageRequest.of(pageNumber, productsPerPage, Sort.Direction.ASC);
            case DESCENDING:
                return PageRequest.of(pageNumber, productsPerPage, Sort.Direction.DESC);
            default:
                throw new IllegalArgumentException(sorting.name() + " sorting type is unsupported");
        }
    }
}
