package com.ecomweb.online.da.DA.controller;

import java.util.List;

import com.ecomweb.online.da.DA.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.ProductService;

@Controller
public class ProductCatalogController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;



    @GetMapping("/catalog")
    public String viewCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            int stock = inventoryService.getStockForProductId(product.getId()); // Fetch stock for each product
            product.setStock(stock); // Set stock in each product
        }
        model.addAttribute("products", products);
        model.addAttribute("edit", edit);
        return "catalog"; // Thymeleaf template name
    }



    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/catalog?edit=true";
    }

    @GetMapping("/products/update-product")
    public String updateProduct() {
        return "update-product"; // Name of the Thymeleaf template (without .html)
    }

}
