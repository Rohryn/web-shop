package com.epam.hrynyshyn.controllers;

import com.epam.hrynyshyn.model.ShoppingCart;
import com.epam.hrynyshyn.model.ShoppingCartInfo;
import com.epam.hrynyshyn.model.entity.Product;
import com.epam.hrynyshyn.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;

import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCT_ID;
import static com.epam.hrynyshyn.constants.Constants.Product.SHOPPING_CART;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
@RestController
public class ShoppingCartController {
    private ProductService productService;

    @Autowired
    public ShoppingCartController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addToCart.do")
    public ShoppingCartInfo addProduct(@RequestParam("product_id") int productId,
                                       @SessionAttribute("shoppingCart") ShoppingCart shoppingCart) {
        Product product = productService.getProductById(productId);
        shoppingCart.addProduct(product);
        return new ShoppingCartInfo(shoppingCart.getProductsCount(), new BigDecimal(shoppingCart.getTotalSum()));
    }

    @PostMapping("/incrementQuantity.do")
    public ShoppingCartInfo incrementProductQuantity(@RequestParam(PRODUCT_ID) int productId,
                                                     @SessionAttribute(SHOPPING_CART) ShoppingCart shoppingCart) {
        shoppingCart.incrementProductQuantity(productId);
        return new ShoppingCartInfo(shoppingCart.getProductsCount(), new BigDecimal(shoppingCart.getTotalSum()));
    }

    @PostMapping("/decrementQuantity.do")
    public ShoppingCartInfo decrementProductQuantity(@RequestParam(PRODUCT_ID) int productId,
                                                     @SessionAttribute(SHOPPING_CART) ShoppingCart shoppingCart) {
        shoppingCart.decrementProductQuantity(productId);
        return new ShoppingCartInfo(shoppingCart.getProductsCount(), new BigDecimal(shoppingCart.getTotalSum()));
    }

    @PostMapping("/removeProduct.do")
    public void removeProduct(@RequestParam(PRODUCT_ID) int productId,
                              @SessionAttribute(SHOPPING_CART) ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(shoppingCart.getProductById(productId));
    }
}
