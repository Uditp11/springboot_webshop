package com.ecomweb.online.da.DA;

import com.ecomweb.online.da.DA.products.Product;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final List<Product> products = Arrays.asList(
        new Product(1, "Adidas", "T-Shirt", 29.99, "M", "Black"),
        new Product(2, "Levi", "Jeans", 49.99, "L", "Blue"),
        new Product(3, "The North Face", "Jacket", 89.99, "XL", "Green"),
        new Product(4, "Nike", "Sneakers", 79.99, "42", "White"),
        new Product(5, "Gucci", "Hat", 44.99, "One Size", "Red")
    );

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
            .filter(product -> product.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @GetMapping("/products/color/{color}")
    public List<Product> getProductsByColor(@PathVariable String color) {
        return products.stream()
            .filter(product -> product.getColor().equalsIgnoreCase(color))
            .collect(Collectors.toList());
    }

    @GetMapping("/products/type/{type}")
    public List<Product> getProductsByType(@PathVariable String type) {
        return products.stream()
            .filter(product -> product.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
    }

    @GetMapping("/products/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return products.stream()
            .filter(product -> product.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }
}
