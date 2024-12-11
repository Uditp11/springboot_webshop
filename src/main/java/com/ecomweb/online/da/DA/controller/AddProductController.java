package com.ecomweb.online.da.DA.controller;


import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class AddProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/new")
    public String getAddProductForm() {
        return "add-product";
    }



    @PostMapping("/add-product")
    public String addProductUI(@RequestParam String name,
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
        inventoryService.setStockForProduct(addedProduct.getId(), 10);
        addedProduct.setStock(10); // Set the default stock value
        model.addAttribute("product", addedProduct);


        return "product-detail";

    }

}
