package com.ecomweb.online.da.DA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.ProductService;

@Controller
public class ProductCatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping("/catalog")
    public String viewCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("edit", edit);
        return "catalog";
    }
    @GetMapping("/product/{id}")
    public String getProductDetailPage(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
        }
        return "product-detail";
    }

    @GetMapping("/add-product")
    public String getAddProductPage() {
        return "add-product";
    }

    @PostMapping("/add-products")
    public String addProduct(@RequestParam String name,
                             @RequestParam String type,
                             @RequestParam double price,
                             @RequestParam String size,
                             @RequestParam String color,
                             Model model) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setType(type);
        newProduct.setPrice(price);
        newProduct.setSize(size);
        newProduct.setColor(color);

        Product addedProduct = productService.addProduct(newProduct);
        model.addAttribute("product", addedProduct);

        return "product-detail";
    }

    // Checking

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/catalog?edit=true";
    }
}
