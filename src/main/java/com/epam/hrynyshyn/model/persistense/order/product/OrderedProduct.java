package com.epam.hrynyshyn.model.persistense.order.product;

import com.epam.hrynyshyn.model.persistense.entity.Product;

public final class OrderedProduct {
    private int orderId;
    private int productId;
    private String name;
    private String manufacturer;
    private String description;
    private int price;
    private int count;

    public OrderedProduct(Product product, int count) {
        productId = product.getId();
        name = product.getName();
        manufacturer = product.getManufacturer().getName();
        description = product.getDescription();
        price = product.getPrice();
        this.count = count;
    }

    public OrderedProduct(int orderId, int productId, String name, String manufacturer, String description, int price, int count) {
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
