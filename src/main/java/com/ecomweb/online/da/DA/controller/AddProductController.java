package com.ecomweb.online.da.DA.controller;


import com.ecomweb.online.da.DA.Facade.AddProductFacade;
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
    private AddProductFacade addProductFacade;

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
                               @RequestParam int stock,
                               Model model) {

        Product addedProduct = addProductFacade.addProduct(name, type, price, size, color, stock);
        model.addAttribute("product", addedProduct);

        return "product-detail";
    }
}
