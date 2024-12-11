package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    public List<Product> getProductsWithStock() {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            int stock = inventoryService.getStockForProductId(product.getId());
            product.setStock(stock);
        }
        return products;
    }

    public void deleteProduct(int productId) {
        productService.deleteProduct(productId);
    }
}
