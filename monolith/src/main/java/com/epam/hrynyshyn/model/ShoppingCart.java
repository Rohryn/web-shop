package com.epam.hrynyshyn.model;


import com.epam.hrynyshyn.exceptions.ProductNotFoundException;
import com.epam.hrynyshyn.model.entity.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.PRODUCT_NOT_FOUND_BY_ID;

public class ShoppingCart {
    private Map<Product, Integer> content;

    public ShoppingCart() {
        content = new LinkedHashMap<>();
    }

    public void addProduct(Product product) {
        if (isProductExists(product)) {
            int count = content.get(product);
            content.replace(product, ++count);
        } else {
            content.put(product, 1);
        }
    }

    public void removeOneProduct(Product product) {
        if (isSeveralProductsExists(product)) {
            int count = content.get(product);
            content.replace(product, --count);
        } else {
            content.remove(product);
        }
    }

    public void removeProduct(Product product) {
        content.remove(product);
    }

    public void clear() {
        content.clear();
    }

    public Product getProductById(int productId) {
        for (Product product : content.keySet()) {
            if (product.getId() == productId) {
                return product;
            }
        }
        throw new ProductNotFoundException(PRODUCT_NOT_FOUND_BY_ID);
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.addAll(content.keySet());
        return products;
    }

    public int getSumForProduct(Product product) {
        int count = content.get(product);
        return product.getPrice() * count;
    }

    public int getTotalCount() {
        int count = 0;
        for (Integer integer : content.values()) {
            count += integer;
        }
        return count;
    }

    public int getProductCount(Product product) {
        return content.get(product);
    }

    public int getProductsCount() {
        return content.keySet().size();
    }

    public int getTotalSum() {
        int sum = 0;
        for (Map.Entry<Product, Integer> entry : content.entrySet()) {
            int price = entry.getKey().getPrice();
            int count = entry.getValue();
            sum += price * count;
        }
        return sum;
    }

    private boolean isProductExists(Product product) {
        return content.containsKey(product);
    }

    private boolean isSeveralProductsExists(Product product) {
        int count = content.get(product);
        return count > 1;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "content=" + content +
                '}';
    }
}
