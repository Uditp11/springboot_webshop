package com.ecomweb.online.da.DA.controller;

import java.util.Collection;

import com.ecomweb.online.da.DA.Facade.CatalogFacade;
import com.ecomweb.online.da.DA.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for serving the SaaS product catalog as JSON.
 */
@RestController
public class SaaSCatalogController {

    @Autowired
    private CatalogFacade catalogFacade;

    /**
     * Returns the list of products that are in stock as JSON.
     *
     * The @CrossOrigin annotation below enables requests from our Svelte
     * frontend (running on http://localhost:5173). Adjust the origins if needed.
     *
     * @return a collection of Product objects.
     */

    @GetMapping("/saas/catalog")
    public Collection<Product> getCatalog() {
        return catalogFacade.getProductsWithStock();
    }
}
