package com.ecomweb.online.da.DA.controller;

import java.util.Collection;
import java.util.List;

import com.ecomweb.online.da.DA.Facade.CatalogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.ecomweb.online.da.DA.model.Product;

/**
 * Controller class for managing product catalog-related functionality.
 * Handles catalog viewing, searching, filtering, and pagination.
 */
@Controller
public class CatalogController {

    /**
     * Facade for managing catalog-related operations.
     */
    @Autowired
    private CatalogFacade catalogFacade;

    /**
     * Displays the catalog with all available products.
     *
     * @param edit  Indicates if the catalog is in edit mode.
     * @param model Spring model object for passing data to the view.
     * @return The Thymeleaf template name for the catalog.
     */
    @GetMapping("/catalog")
    public String viewCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        Collection<Product> products = catalogFacade.getProductsWithStock();
        model.addAttribute("products", products);
        model.addAttribute("edit", edit);
        return "catalog";
    }

    /**
     * Searches products by name and displays them.
     *
     * @param name  The name of the product to search.
     * @param model Spring model object for passing data to the view.
     * @return The Thymeleaf template name for the catalog.
     */
    @GetMapping("/products/search")
    public String searchProducts(@RequestParam("name") String name, Model model) {
        List<Product> products = catalogFacade.searchProductsByName(name);
        model.addAttribute("products", products);
        return "catalog";
    }

    /**
     * Deletes a product by its ID and redirects to the catalog page.
     *
     * @param id The ID of the product to delete.
     * @return A redirect to the catalog page in edit mode.
     */
    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        catalogFacade.deleteProduct(id);
        return "redirect:/catalog-paginated?edit=true";
    }

    /**
     * Displays the product update form.
     *
     * @return The Thymeleaf template name for updating products.
     */
    @GetMapping("/products/update-product")
    public String updateProduct() {
        return "update-product";
    }

    /**
     * Filters products by color and displays the results.
     *
     * @param color The color to filter products by.
     * @param model Spring model object for passing data to the view.
     * @return The Thymeleaf template name for the catalog.
     */
    @GetMapping("/products/filter")
    public String filterProductsByColor(@RequestParam("color") String color, Model model) {
        List<Product> products = catalogFacade.getProductsByColor(color);
        model.addAttribute("products", products);
        return "catalog";
    }

    /**
     * Displays a paginated catalog of products.
     *
     * @param pageable The Pageable object containing pagination information.
     * @param edit     Indicates if the catalog is in edit mode.
     * @param model    Spring model object for passing data to the view.
     * @return The Thymeleaf template name for the paginated catalog.
     */
    @GetMapping("/catalog-paginated")
    public String viewPaginatedCatalog(
            @RequestParam(required = false, defaultValue = "false") boolean edit,
            @PageableDefault(size = 3) Pageable pageable,
            Model model) {

        Page<Product> productPage = catalogFacade.getPaginatedProducts(pageable);
        model.addAttribute("productPage", productPage);
        model.addAttribute("edit", edit);
        return "catalog-paginated";
    }
}
