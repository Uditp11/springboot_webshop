package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.AddProductFacade;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing product addition functionality.
 * Handles requests related to adding new products.
 */
@Controller
@RequestMapping("/products")
public class AddProductController {

    /**
     * Facade for managing product addition logic.
     */
    @Autowired
    private AddProductFacade addProductFacade;

    /**
     * Displays the product addition form.
     *
     * @return The name of the HTML template for adding products.
     */
    @GetMapping("/new")
    public String getAddProductForm() {
        return "add-product";
    }

    /**
     * Handles product addition requests and displays product details.
     *
     * @param name  The product's name.
     * @param type  The type/category of the product.
     * @param price The price of the product.
     * @param size  The size of the product.
     * @param color The color of the product.
     * @param stock The quantity of the product in stock.
     * @param model Spring model object for passing data to the view.
     * @return The name of the HTML template displaying product details.
     */
    @PostMapping("/add-product")
    public String addProductUI(@RequestParam String name,
                               @RequestParam String type,
                               @RequestParam double price,
                               @RequestParam String size,
                               @RequestParam String color,
                               @RequestParam int stock,
                               Model model) {

        // Add the product using the facade
        ProductDetailDTO productDetail = addProductFacade.addProduct(name, type, price, size, color, stock);

        // Check if the product was added successfully and pass it to the model
        if (productDetail != null) {
            model.addAttribute("productDetailDTO", productDetail);
        }

        return "product-detail";
    }

}
