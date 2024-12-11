package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;



    @GetMapping("/products/{id}")
    public String getProductDetailPage(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            int stock = inventoryService.getStockForProductId(id); // Fetch stock from InventoryService
            product.setStock(stock); // Set stock in the product object
            model.addAttribute("product", product);
        }
        return "product-detail"; // Thymeleaf template name
    }


}
