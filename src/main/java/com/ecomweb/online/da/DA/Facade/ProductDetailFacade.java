package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;

    // Constructor-based Dependency Injection
    public ProductDetailFacade(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    public ProductDetailDTO getProductWithStock(long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            int stock = inventoryService.getStockForProductId(productId);
            return new ProductDetailDTO(product, stock);
        }
        return null;
    }
}
