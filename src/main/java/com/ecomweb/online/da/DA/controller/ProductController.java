package com.ecomweb.online.da.DA.controller;

import java.util.List;

import com.ecomweb.online.da.DA.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    // ===== REST API Endpoints =====

    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        inventoryService.setStockForProduct(addedProduct.getId(), 10); // Default stock initialization
        return ResponseEntity.ok(addedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable int id) {
        List<Product> remainingProducts = productService.deleteProduct(id);
        return ResponseEntity.ok(remainingProducts);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("/type/{type}")
    @ResponseBody
    public List<Product> getProductsByType(@PathVariable String type) {
        return productService.getProductsByType(type);
    }

    @GetMapping("/size/{size}")
    @ResponseBody
    public List<Product> getProductsBySize(@PathVariable String size) {
        return productService.getProductsBySize(size);
    }

    @GetMapping("/color/{color}")
    @ResponseBody
    public List<Product> getProductsByColor(@PathVariable String color) {
        return productService.getProductsByColor(color);
    }

    // ===== Inventory Management Endpoints =====

    @PostMapping("/{id}/set-stock")
    @ResponseBody
    public ResponseEntity<String> setProductStock(@PathVariable int id, @RequestParam int stock) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        inventoryService.setStockForProduct(id, stock);
        return ResponseEntity.ok("Stock initialized for product with ID: " + id);
    }

    @GetMapping("/{id}/stock")
    @ResponseBody
    public ResponseEntity<Integer> getProductStock(@PathVariable int id) {
        int stock = inventoryService.getStockForProductId(id);
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/{id}/reduce-stock")
    @ResponseBody
    public ResponseEntity<String> reduceProductStock(@PathVariable int id) {
        boolean success = inventoryService.reduceStockForProductId(id);
        if (!success) {
            return ResponseEntity.badRequest().body("Stock for product with ID: " + id + " is already zero.");
        }
        return ResponseEntity.ok("Stock reduced for product with ID: " + id);
    }

    // ===== UI Endpoints =====

    @GetMapping("/catalog")
    public String viewCatalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            int stock = inventoryService.getStockForProductId(product.getId()); // Fetch stock for each product
            product.setStock(stock); // Set stock in each product
        }
        model.addAttribute("products", products);
        model.addAttribute("edit", edit);
        return "catalog"; // Thymeleaf template name
    }

    @GetMapping("/product/{id}")
    public String getProductDetailPage(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            int stock = inventoryService.getStockForProductId(id); // Fetch stock from InventoryService
            product.setStock(stock); // Set stock in the product object
            model.addAttribute("product", product);
        }
        return "product-detail"; // Thymeleaf template name
    }

    @GetMapping("/add-product")
    public String getAddProductPage() {
        return "add-product";
    }

    @PostMapping("/add-products")
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
        inventoryService.setStockForProduct(addedProduct.getId(), 10); // Default stock initialization
        addedProduct.setStock(10); // Set the default stock value
        model.addAttribute("product", addedProduct);

        return "product-detail";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProductUI(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products/catalog?edit=true";
    }
}
