package com.ecomweb.online.da.DA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private double price;
    private String size;
    private String color;

    // Constructor without stock initialization
    public Product(long id, String name, String type, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    // Default constructor
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
