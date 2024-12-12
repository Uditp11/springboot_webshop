package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.ProductDetailFacade;
import com.ecomweb.online.da.DA.model.ProductDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller class for managing product details.
 * Handles requests related to viewing detailed information about a product.
 */
@Controller
public class ProductDetailController {

    /**
     * Facade for managing product detail operations.
     */
    @Autowired
    private ProductDetailFacade productDetailFacade;

    /**
     * Displays the product detail page for a specific product.
     *
     * @param id    The ID of the product to view details for.
     * @param model Spring model object for passing data to the view.
     * @return The Thymeleaf template name for the product detail page.
     */
    @GetMapping("/products/{id}")
    public String getProductDetailPage(@PathVariable int id, Model model) {
        ProductDetailDTO productDetail = productDetailFacade.getProductWithStock(id);
        if (productDetail != null) {
            model.addAttribute("productDetailDTO", productDetail);
        }
        return "product-detail";
    }
}
