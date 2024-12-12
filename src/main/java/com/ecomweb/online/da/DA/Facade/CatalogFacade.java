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

    @Autowired
    private InventoryService inventoryService;

    public Collection<Product> getProductsWithStock() {
        Collection<Product> products = productService.getAllProducts();
        return products;
    }

    public List<Product> searchProductsByName(String name) {
        return productService.fetchProductsByName(name);
    }

    public void deleteProduct(long productId) {
        productService.deleteProductById(productId); // Updated method name
        inventoryService.removeStockForProduct(productId);
    }

    // New method to filter products by color
    public List<Product> getProductsByColor(String color) {
        return productService.getProductsByColor(color);
    }
}
