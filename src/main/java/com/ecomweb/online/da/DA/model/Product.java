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

    public Product() {}

    public int getId() { return id;}
    public String getName() { return name;}
    public String getType() { return type;}
    public double getPrice() { return price;}
    public String getSize() { return size;}
    public String getColor() { return color;}

    public void setId(int id) { this.id = id;}
    public void setName(String name) { this.name = name;}
    public void setType(String type) { this.type = type;}
    public void setPrice(double price) { this.price = price;}
    public void setSize(String size) { this.size = size;}
    public void setColor(String color) { this.color = color;}
}