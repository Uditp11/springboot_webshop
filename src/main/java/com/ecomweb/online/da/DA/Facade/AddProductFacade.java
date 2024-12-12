package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Facade class for managing product addition.
 * Combines product creation and inventory management into a single operation.
 */
@Component
public class AddProductFacade {

    /**
     * Service for managing product-related operations.
     */
    @Autowired
    private ProductService productService;

    /**
     * Service for managing inventory-related operations.
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * Adds a new product and updates its stock.
     *
     * @param name  The product's name.
     * @param type  The type/category of the product.
     * @param price The product's price.
     * @param size  The size of the product.
     * @param color The color of the product.
     * @param stock The quantity of the product in stock.
     * @return A DTO containing the product details and stock information.
     */
    public ProductDetailDTO addProduct(String name, String type, double price, String size, String color, int stock) {
        // Create a new product and set its properties
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setType(type);
        newProduct.setPrice(price);
        newProduct.setSize(size);
        newProduct.setColor(color);

        // Save the new product and update its stock
        Product savedProduct = productService.addProduct(newProduct);
        inventoryService.setStockForProduct(savedProduct.getId(), stock);

        // Return a DTO containing the saved product and stock information
        return new ProductDetailDTO(savedProduct, stock);
    }
}
