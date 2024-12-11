package com.ecomweb.online.da.DA.model;

public class Product {

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
}
