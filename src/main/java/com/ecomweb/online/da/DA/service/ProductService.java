package com.ecomweb.online.da.DA.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecomweb.online.da.DA.model.Product;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>(List.of(
            new Product(1, "Adidas", "T-Shirt", 29.99, "M", "Black"),
            new Product(2, "Levi", "Jeans", 49.99, "L", "Blue"),
            new Product(3, "The North Face", "Jacket", 89.99, "XL", "Green"),
            new Product(4, "Nike", "Sneakers", 79.99, "42", "White"),
            new Product(5, "Gucci", "Hat", 44.99, "One Size", "Red"),
            new Product(6, "Puma", "Shorts", 24.99, "M", "Gray"),
            new Product(7, "Zara", "Blazer", 119.99, "L", "Navy")
    ));

    private int nextId = products.stream().mapToInt(Product::getId).max().orElse(0);

    public List<Product> getAllProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        product.setId(++nextId);
        products.add(product);
        return product;
    }

    public List<Product> deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
        return products;
    }

    public Product updateProduct(Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == updatedProduct.getId()) {
                product.setName(updatedProduct.getName());
                product.setType(updatedProduct.getType());
                product.setPrice(updatedProduct.getPrice());
                product.setSize(updatedProduct.getSize());
                product.setColor(updatedProduct.getColor());
                return product;
            }
        }
        return null;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProductsByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByType(String type) {
        return products.stream()
                .filter(product -> product.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsBySize(String size) {
        return products.stream()
                .filter(product -> product.getSize().equalsIgnoreCase(size))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByColor(String color) {
        return products.stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
