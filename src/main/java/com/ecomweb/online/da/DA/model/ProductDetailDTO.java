package com.ecomweb.online.da.DA.model;

/**
 * Data Transfer Object (DTO) representing detailed product information.
 * Combines product details with stock availability status.
 */
public class ProductDetailDTO {

    /**
     * The product associated with this DTO.
     */
    private Product product;

    /**
     * The current stock quantity of the product.
     */
    private int stock;

    /**
     * Indicates if the product is sold out.
     */
    private boolean isSoldOut;

    /**
     * Constructs a ProductDetailDTO with the specified product and stock quantity.
     *
     * @param product The product instance.
     * @param stock   The current stock quantity.
     */
    public ProductDetailDTO(Product product, int stock) {
        this.product = product;
        this.stock = stock;
        this.isSoldOut = stock == 0;
    }

    /**
     * Retrieves the product associated with this DTO.
     *
     * @return The product instance.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with this DTO.
     *
     * @param product The product instance.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Retrieves the current stock quantity of the product.
     *
     * @return The stock quantity.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Updates the stock quantity and adjusts the sold-out status.
     *
     * @param stock The new stock quantity.
     */
    public void setStock(int stock) {
        this.stock = stock;
        this.isSoldOut = stock == 0;
    }

    /**
     * Checks if the product is sold out.
     *
     * @return true if the product is sold out, false otherwise.
     */
    public boolean isSoldOut() {
        return isSoldOut;
    }

    /**
     * Sets the sold-out status of the product.
     *
     * @param soldOut The new sold-out status.
     */
    public void setSoldOut(boolean soldOut) {
        this.isSoldOut = soldOut;
    }
}
