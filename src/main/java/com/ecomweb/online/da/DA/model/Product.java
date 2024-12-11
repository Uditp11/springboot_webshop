package com.ecomweb.online.da.DA.model;

public class Product {

    private int id;
    private String name;
    private String type;
    private double price;
    private String size;
    private String color;

    // Mark 'stock' as transient since it's managed dynamically via InventoryService
    private transient int stock;

    // Constructor without stock initialization
    public Product(int id, String name, String type, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.size = size;
        this.color = color;
        this.stock = 0; // Default value, actual stock comes from InventoryService
    }

    // Default constructor
    public Product() {
        this.stock = 0; // Default value
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public int getStock() { return stock; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setPrice(double price) { this.price = price; }
    public void setSize(String size) { this.size = size; }
    public void setColor(String color) { this.color = color; }
    public void setStock(int stock) { this.stock = stock; }
}
