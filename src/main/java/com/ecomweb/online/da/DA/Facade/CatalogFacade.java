package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CatalogFacade {

    @Autowired
    private ProductService productService;

    @Autowired InventoryService inventoryService;


    public Collection<Product> getProductsWithStock() {
        Collection<Product> products = productService.getAllProducts();
        return products;
    }

    public void deleteProduct(long productId) {
        productService.deleteProduct(productId);
        inventoryService.removeStockForProduct(productId);
    }
}
