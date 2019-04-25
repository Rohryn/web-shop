package com.epam.hrynyshyn.controllers;

import com.epam.hrynyshyn.controllers.products.ProductsPageServlet;
import com.epam.hrynyshyn.services.CategoryService;
import com.epam.hrynyshyn.services.ManufacturerService;
import com.epam.hrynyshyn.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORIES;
import static com.epam.hrynyshyn.constants.Constants.Product.CURRENT_PAGE;
import static com.epam.hrynyshyn.constants.Constants.Product.DEFAULT_PRODUCT_PER_PAGE;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURERS;
import static com.epam.hrynyshyn.constants.Constants.Product.MAX_PRICE;
import static com.epam.hrynyshyn.constants.Constants.Product.MIN_PRICE;
import static com.epam.hrynyshyn.constants.Constants.Product.NUMBER_OF_PAGES;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCTS;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCTS_COUNT;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCTS_PER_PAGE;
import static com.epam.hrynyshyn.constants.Constants.Product.SORT;
import static com.epam.hrynyshyn.constants.Constants.Product.UNSORTED;
import static com.epam.hrynyshyn.util.RequestParametersValidator.isArrayValid;
import static com.epam.hrynyshyn.util.RequestParametersValidator.isParameterExists;
import static com.epam.hrynyshyn.util.RequestParametersValidator.isPositiveNumber;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
// TODO: 2019-04-17 refactor
@Controller
public class ProductController {
    private static Logger logger = Logger.getLogger(ProductsPageServlet.class);
    private ProductService productService;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             ManufacturerService manufacturerService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products.do")
    public void getProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        preparePageContent(req);
        prepareProductsListNavigation(req);
        req.getRequestDispatcher("/WEB-INF/products.jsp").forward(req, resp);

    }

    private void preparePageContent(HttpServletRequest request) {
        Map<String, Object> parameters = extractQueryParameters(request);
        request.setAttribute(MANUFACTURERS, manufacturerService.getAllManufacturers());
        request.setAttribute(CATEGORIES, categoryService.getAllCategories());
        request.setAttribute(PRODUCTS, productService.getProductsByQueryParameters(parameters));

    }

    private void prepareProductsListNavigation(HttpServletRequest request) {
        Map<String, Object> parameters = extractQueryParameters(request);
        int productsCount = productService.getProductsCountBySelectParameters(parameters);
        int currentPage = getCurrentPage(request);
        int productsPerPage = getProductsPerPage(request);
        int numberOfPages = calculateNumberOfPages(productsCount, productsPerPage);
        if (currentPage > numberOfPages) {
            currentPage = numberOfPages;
        }
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(PRODUCTS_COUNT, productsCount);
    }

    private Map<String, Object> extractQueryParameters(HttpServletRequest request) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        String[] manufacturers = request.getParameterValues(MANUFACTURERS);
        if (isArrayValid(manufacturers)) {
            parameters.put(MANUFACTURERS, Arrays.asList(manufacturers));
        }
        String[] categories = request.getParameterValues(CATEGORIES);
        if (isArrayValid(categories)) {
            parameters.put(CATEGORIES, Arrays.asList(categories));
        }
        String minPrice = request.getParameter(MIN_PRICE);
        if (isPositiveNumber(minPrice)) {
            parameters.put(MIN_PRICE, Integer.parseInt(minPrice));
        }
        String maxPrice = request.getParameter(MAX_PRICE);
        if (isPositiveNumber(maxPrice)) {
            parameters.put(MAX_PRICE, Integer.parseInt(maxPrice));
        }
        String sort = request.getParameter(SORT);
        if (isParameterExists(sort) && !sort.equals(UNSORTED)) {
            parameters.put(SORT, sort);
        }
        String productsPerPage = request.getParameter(PRODUCTS_PER_PAGE);
        if (isPositiveNumber(productsPerPage)) {
            parameters.put(PRODUCTS_PER_PAGE, Integer.parseInt(productsPerPage));
        }
        String currentPage = request.getParameter(CURRENT_PAGE);
        if (isPositiveNumber(currentPage)) {
            parameters.put(CURRENT_PAGE, Integer.parseInt(currentPage));
        }
        logger.info(parameters.toString());
        return parameters;
    }

    private int getProductsPerPage(HttpServletRequest request) {
        int productsPerPage = DEFAULT_PRODUCT_PER_PAGE;
        String inputProductsPerPage = request.getParameter(PRODUCTS_PER_PAGE);
        if (isPositiveNumber(inputProductsPerPage)) {
            productsPerPage = Integer.parseInt(inputProductsPerPage);
        }
        return productsPerPage;
    }

    private int getCurrentPage(HttpServletRequest request) {
        int currentPage = 1;
        String pageFromRequest = request.getParameter(CURRENT_PAGE);
        if (isPositiveNumber(pageFromRequest)) {
            currentPage = Integer.parseInt(pageFromRequest);
        }
        return currentPage;
    }

    private int calculateNumberOfPages(int productsCount, int productsPerPage) {
        return (int) Math.ceil(productsCount * 1.0 / productsPerPage);
    }
}
