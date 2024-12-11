package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.ProductDetailFacade;
import com.ecomweb.online.da.DA.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailController {

    @Autowired
    private ProductDetailFacade productDetailFacade;

    @GetMapping("/products/{id}")
    public String getProductDetailPage(@PathVariable int id, Model model) {
        Product product = productDetailFacade.getProductWithStock(id);
        if (product != null) {
            model.addAttribute("product", product);
        }
        return "product-detail"; // Thymeleaf template name
    }

  
}
