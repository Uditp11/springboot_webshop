package com.ecomweb.online.da.DA.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecomweb.online.da.DA.model.Product;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>(Map.of(
            1L, new Product(1L, "Adidas", "T-Shirt", 29.99, "M", "Black"),
            2L, new Product(2L, "Levi", "Jeans", 49.99, "L", "Blue"),
            3L, new Product(3L, "The North Face", "Jacket", 89.99, "XL", "Green"),
            4L, new Product(4L, "Nike", "Sneakers", 79.99, "42", "White"),
            5L, new Product(5L, "Gucci", "Hat", 44.99, "One Size", "Red"),
            6L, new Product(6L, "Puma", "Shorts", 24.99, "M", "Gray"),
            7L, new Product(7L, "Zara", "Blazer", 119.99, "L", "Navy")
    ));

    private long nextId = products.keySet().stream().mapToLong(id -> id).max().orElse(0);

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Product addProduct(Product product) {
        product.setId(++nextId);
        products.put(product.getId(), product);
        return product;
    }

    public Collection<Product> deleteProduct(long id) {
        products.remove(id);
        return getAllProducts();
    }

    public Product updateProduct(Product updatedProduct) {
        if (products.containsKey(updatedProduct.getId())) {
            products.put(updatedProduct.getId(), updatedProduct);
            return updatedProduct;
        }
        return null;
    }

    public Product getProductById(long id) {
        return products.get(id);
    }

    public List<Product> getProductsByName(String name) {
        return products.values().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByType(String type) {
        return products.values().stream()
                .filter(product -> product.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsBySize(String size) {
        return products.values().stream()
                .filter(product -> product.getSize().equalsIgnoreCase(size))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByColor(String color) {
        return products.values().stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
