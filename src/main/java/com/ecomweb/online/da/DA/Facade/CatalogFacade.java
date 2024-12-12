package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * Facade class for managing catalog-related operations.
 * Combines product management and inventory handling.
 */
@Component
public class CatalogFacade {

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
     * Retrieves all products with stock information.
     *
     * @return A collection of all products.
     */
    public Collection<Product> getProductsWithStock() {
        return productService.getAllProducts();
    }

    /**
     * Searches products by their name.
     *
     * @param name The name of the product to search for.
     * @return A list of products matching the given name.
     */
    public List<Product> searchProductsByName(String name) {
        return productService.fetchProductsByName(name);
    }

    /**
     * Deletes a product by its ID and removes associated stock.
     *
     * @param productId The ID of the product to delete.
     */
    public void deleteProduct(long productId) {
        productService.deleteProductById(productId);
        inventoryService.removeStockForProduct(productId);
    }

    /**
     * Retrieves products filtered by color.
     *
     * @param color The color to filter products by.
     * @return A list of products matching the specified color.
     */
    public List<Product> getProductsByColor(String color) {
        return productService.getProductsByColor(color);
    }

    /**
     * Retrieves a paginated list of products.
     *
     * @param pageable The pagination information.
     * @return A page of products.
     */
    public Page<Product> getPaginatedProducts(Pageable pageable) {
        return productService.getPaginatedProducts(pageable);
    }
}
