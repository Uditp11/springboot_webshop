package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    public Product getProductWithStock(int productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            int stock = inventoryService.getStockForProductId(productId);
            product.setStock(stock);
        }
        return product;
    }


}
