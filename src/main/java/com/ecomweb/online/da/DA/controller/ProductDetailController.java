package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.ProductDetailFacade;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
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
        ProductDetailDTO productDetail = productDetailFacade.getProductWithStock(id);
        if (productDetail != null) {
            model.addAttribute("productDetailDTO", productDetail);
        }
        return "product-detail"; // Thymeleaf template name
    }
}
