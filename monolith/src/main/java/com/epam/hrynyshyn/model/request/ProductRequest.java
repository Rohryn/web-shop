package com.epam.hrynyshyn.model.request;

import com.epam.hrynyshyn.model.Sorting;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
public class ProductRequest {
    private List<String> manufacturers;
    private List<String> categories;
    private BigDecimal minimalPrice;
    private BigDecimal maximalPrice;
    private Sorting sorting;
    private int productsPerPage;
    private int pageNumber;

    public ProductRequest(List<String> manufacturers,
                          List<String> categories,
                          BigDecimal minimalPrice,
                          BigDecimal maximalPrice,
                          Sorting sorting,
                          int productsPerPage,
                          int pageNumber) {
        this.manufacturers = manufacturers;
        this.categories = categories;
        this.minimalPrice = minimalPrice;
        this.maximalPrice = maximalPrice;
        this.sorting = sorting;
        this.productsPerPage = productsPerPage;
        this.pageNumber = pageNumber;
    }

    public List<String> getManufacturers() {
        return manufacturers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public BigDecimal getMinimalPrice() {
        return minimalPrice;
    }

    public BigDecimal getMaximalPrice() {
        return maximalPrice;
    }

    public Sorting getSorting() {
        return sorting;
    }

    public int getProductsPerPage() {
        return productsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
