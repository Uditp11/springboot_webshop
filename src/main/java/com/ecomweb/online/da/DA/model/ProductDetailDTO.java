package com.ecomweb.online.da.DA.model;

public class ProductDetailDTO {

    private Product product;
    private int stock;
    private boolean isSoldOut;

    public ProductDetailDTO(Product product, int stock) {
        this.product = product;
        this.stock = stock;
        this.isSoldOut = stock == 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        this.isSoldOut = stock == 0;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}
