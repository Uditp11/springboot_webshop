package com.ecomweb.online.da.DA.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing product-related operations.
 * Provides CRUD, search, filtering, and pagination functionality.
 */
@Service
public class ProductService {

    /**
     * Repository for accessing product data.
     */
    private final ProductRepository productRepository;

    /**
     * Constructor-based Dependency Injection.
     *
     * @param productRepository The repository for product data.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Searches products by name using a custom query.
     *
     * @param name The product name to search for.
     * @return A list of matching products.
     */
    public List<Product> fetchProductsByName(String name) {
        return productRepository.fetchAllProductsWithName(name);
    }

    /**
     * Retrieves a product by its unique ID.
     *
     * @param id The product's unique identifier.
     * @return The product if found, or null otherwise.
     */
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new product to the repository.
     *
     * @param product The product to be added.
     * @return The saved product instance.
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product if it exists.
     *
     * @param product The updated product details.
     * @return The updated product, or null if it does not exist.
     */
    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        }
        return null;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The product's unique identifier.
     */
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    /**
     * Retrieves products filtered by color.
     *
     * @param color The color filter.
     * @return A list of matching products.
     */
    public List<Product> getProductsByColor(String color) {
        return productRepository.findByColorIgnoreCase(color);
    }

    /**
     * Retrieves products filtered by name.
     *
     * @param name The name filter.
     * @return A list of matching products.
     */
    public List<Product> getProductsByName(String name) {
        return productRepository.findAll().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .toList();
    }

    /**
     * Retrieves products filtered by type.
     *
     * @param type The type filter.
     * @return A list of matching products.
     */
    public List<Product> getProductsByType(String type) {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().equalsIgnoreCase(type))
                .toList();
    }

    /**
     * Retrieves products filtered by size.
     *
     * @param size The size filter.
     * @return A list of matching products.
     */
    public List<Product> getProductsBySize(String size) {
        return productRepository.findAll().stream()
                .filter(product -> product.getSize().equalsIgnoreCase(size))
                .toList();
    }

    /**
     * Retrieves paginated products from the repository.
     *
     * @param pageable Pagination details.
     * @return A paginated list of products.
     */
    public Page<Product> getPaginatedProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
