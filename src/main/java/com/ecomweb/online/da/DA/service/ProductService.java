package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

import com.ecomweb.online.da.DA.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public Product getProductById(List<Product> products, int id) {
        return products.stream()
            .filter(product -> product.getId() == id)
            .findFirst()
            .orElse(null); 
    }

    public List<Product> getProductsByName(List<Product> products, String name) {
        return products.stream()
            .filter(product -> product.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    public List<Product> getProductsByType(List<Product> products, String type) {
        return products.stream()
            .filter(product -> product.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
    }

    public List<Product> getProductsBySize(List<Product> products, String size) {
        return products.stream()
            .filter(product -> product.getSize().equalsIgnoreCase(size))
            .collect(Collectors.toList());
    }

    public List<Product> getProductsByColor(List<Product> products, String color) {
        return products.stream()
            .filter(product -> product.getColor().equalsIgnoreCase(color))
            .collect(Collectors.toList());
    }
}
