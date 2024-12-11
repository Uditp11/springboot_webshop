package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    public ProductDetailDTO addProduct(String name, String type, double price, String size, String color, int stock) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setType(type);
        newProduct.setPrice(price);
        newProduct.setSize(size);
        newProduct.setColor(color);

        Product savedProduct = productService.addProduct(newProduct);
        inventoryService.setStockForProduct(savedProduct.getId(), stock);


        return new ProductDetailDTO(savedProduct, stock);

    }
}

