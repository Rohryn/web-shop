package com.epam.hrynyshyn.model.order;

import com.epam.hrynyshyn.model.entity.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderedProducts")
public final class OrderedProduct {
    @Id
    @GeneratedValue
    private int id;
    private int orderId;
    private int productId;
    private String name;
    private int manufacturerId;
    private String description;
    private int price;
    private int count;

    public OrderedProduct(Product product, int count) {
        productId = product.getId();
        name = product.getName();
        manufacturerId = product.getManufacturerId();
        description = product.getDescription();
        price = product.getPrice();
        this.count = count;
    }

    public OrderedProduct(int orderId, int productId, String name, int manufacturerId, String description, int price, int count) {
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.manufacturerId = manufacturerId;
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

    public int getManufacturerId() {
        return manufacturerId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
