package com.ecomweb.online.da.DA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Entity class representing a Product in the e-commerce system.
 * Contains attributes related to the product's properties and metadata.
 */
@Entity
public class Product {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Category or type of the product.
     */
    private String type;

    /**
     * Price of the product.
     */
    private double price;

    /**
     * Size of the product.
     */
    private String size;

    /**
     * Color of the product.
     */
    private String color;

    /**
     * Constructor for initializing all product attributes.
     *
     * @param id    Unique product ID.
     * @param name  Product name.
     * @param type  Product category/type.
     * @param price Product price.
     * @param size  Product size.
     * @param color Product color.
     */
    public Product(long id, String name, String type, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    /**
     * Default constructor initializing attributes with default values.
     */
    public Product() {
        this.id = 0;
        this.name = "";
        this.type = "";
        this.price = 0.0;
        this.size = "";
        this.color = "";
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    public String getColor() { return color; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setPrice(double price) { this.price = price; }
    public void setSize(String size) { this.size = size; }
    public void setColor(String color) { this.color = color; }

    /**
     * Compares this product to another for equality based on ID.
     *
     * @param o The object to compare.
     * @return true if the IDs match, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    /**
     * Generates a hash code based on the product's ID.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
