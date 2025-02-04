package com.ecomweb.online.da.DA.model;

public class Order {

    private double totalPrice;
    private int userId;

    public Order(double totalPrice, int userId) {
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
