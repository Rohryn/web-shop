package com.epam.hrynyshyn.controllers;

import com.epam.hrynyshyn.model.Sorting;
import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.model.request.ProductRequest;
import com.epam.hrynyshyn.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-23.
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getProducts(@RequestParam(value = "manufacturers", required = false) List<String> manufacturers,
                                     @RequestParam(value = "categories", required = false) List<String> categories,
                                     @RequestParam(value = "minimalPrice", required = false) BigDecimal minimalPrice,
                                     @RequestParam(value = "maximalPrice", required = false) BigDecimal maximalPrice,
                                     @RequestParam(value = "sort", required = false, defaultValue = "unsorted") String sorting,
                                     @RequestParam(value = "productsOnPage", required = false, defaultValue = "9") Integer productsPerPage,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        ProductRequest request = new ProductRequest(
                manufacturers,
                categories,
                minimalPrice,
                maximalPrice,
                Sorting.fromName(sorting),
                productsPerPage,
                pageNumber);

        return productService.getProducts(request);
    }
}
