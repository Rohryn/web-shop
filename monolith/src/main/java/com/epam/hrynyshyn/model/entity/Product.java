package com.epam.hrynyshyn.model.entity;

import java.util.Objects;

/**
 * Created by Roman_Hrynyshyn on 12-Dec-16.
 */
public class Product {
    private int id;
    private String name;
    private int manufacturerId;
    private int categoryId;
    private String description;
    private String imageSource;
    // TODO: 2019-04-17 bigdecimal
    private int price;

    public Product(int id, String name, int manufacturerId, int categoryId, String description, String imageSource, int price) {
        this.id = id;
        this.name = name;
        this.manufacturerId = manufacturerId;
        this.categoryId = categoryId;
        this.description = description;
        this.imageSource = imageSource;
        this.price = price;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                Objects.equals(name, product.name) &&
                Objects.equals(manufacturerId, product.manufacturerId) &&
                Objects.equals(categoryId, product.categoryId) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imageSource, product.imageSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturerId, categoryId, description, imageSource, price);
    }
}
