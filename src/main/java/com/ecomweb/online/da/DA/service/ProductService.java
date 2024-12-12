package com.ecomweb.online.da.DA.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> fetchProductsByName(String name) {
        return productRepository.fetchAllProductsWithName(name);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    // New method to filter products by color
    public List<Product> getProductsByColor(String color) {
        return productRepository.findByColorIgnoreCase(color);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findAll().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .toList();
    }

    public List<Product> getProductsByType(String type) {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().equalsIgnoreCase(type))
                .toList();
    }

    public List<Product> getProductsBySize(String size) {
        return productRepository.findAll().stream()
                .filter(product -> product.getSize().equalsIgnoreCase(size))
                .toList();
    }

}
