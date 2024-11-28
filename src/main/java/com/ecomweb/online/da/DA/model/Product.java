package com.ecomweb.online.da.DA.model;

public class Product {
    private int id;
    private String name;
    private String type;
    private double price;
    private String size;
    private String color;

    public Product(int id, String name, String type, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    public String getColor() { return color; }
}
