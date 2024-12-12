package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * Facade class for managing detailed product information.
 * Combines product data with stock information.
 */
@Service
public class ProductDetailFacade {

    /**
     * Service for managing product-related operations.
     */
    private final ProductService productService;

    /**
     * Service for managing inventory-related operations.
     */
    private final InventoryService inventoryService;

    /**
     * Constructor-based Dependency Injection for services.
     *
     * @param productService    The service for handling product-related logic.
     * @param inventoryService  The service for managing inventory stock.
     */
    public ProductDetailFacade(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves detailed product information, including stock.
     *
     * @param productId The ID of the product to fetch details for.
     * @return A ProductDetailDTO containing the product and its stock, or null if the product is not found.
     */
    public ProductDetailDTO getProductWithStock(long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            int stock = inventoryService.getStockForProductId(productId);
            return new ProductDetailDTO(product, stock);
        }
        return null;
    }
}