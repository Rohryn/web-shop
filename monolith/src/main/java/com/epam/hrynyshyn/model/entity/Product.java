package com.epam.hrynyshyn.model.entity;

/**
 * Created by Roman_Hrynyshyn on 12-Dec-16.
 */
public class Product {
    private int id;
    private String name;
    private Manufacturer manufacturer;
    private Category category;
    private String description;
    private String imageSource;
    private int price;

    public Product(int id, String name, Manufacturer manufacturer, Category category, String description, String imageSource, int price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

        if (!name.equals(product.name)) return false;
        if (!manufacturer.equals(product.manufacturer)) return false;
        if (!category.equals(product.category)) return false;
        return description.equals(product.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
