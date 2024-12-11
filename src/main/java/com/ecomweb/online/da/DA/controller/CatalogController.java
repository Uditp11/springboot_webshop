package com.ecomweb.online.da.DA.controller;

import java.util.Collection;
import java.util.List;

import com.ecomweb.online.da.DA.Facade.CatalogFacade;
import com.ecomweb.online.da.DA.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecomweb.online.da.DA.model.Product;


@Controller
public class CatalogController {

    @Autowired
    private CatalogFacade catalogFacade;

    @GetMapping("/catalog")
    public String viewCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        Collection<Product> products = catalogFacade.getProductsWithStock();
        model.addAttribute("products", products);
        model.addAttribute("edit", edit);
        return "catalog"; // Thymeleaf template name
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        catalogFacade.deleteProduct(id);
        return "redirect:/catalog?edit=true";
    }

    @GetMapping("/products/update-product")
    public String updateProduct() {
        return "update-product"; // Name of the Thymeleaf template (without .html)
    }
}
