package com.ecomweb.online.da.DA.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.repository.ProductRepository;

import java.math.BigDecimal;

/**
 * Configuration class responsible for loading initial product data
 * into the in-memory H2 database when the application starts.
 */
@Configuration
public class LoadProductDatabase {

    /**
     * Logger for logging product loading activities.
     */
    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);

    /**
     * Initializes the H2 database with predefined product entries.
     *
     * @param repository The product repository used to save initial product data.
     * @return CommandLineRunner instance for executing database initialization at startup.
     */
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            // Preloading product entries into the database
            repository.save(new Product(0, "Adidas", "T-Shirt", new BigDecimal("29.99"), "M", "Black"));
            repository.save(new Product(0, "Levi", "Jeans", new BigDecimal("49.99"), "L", "Blue"));
            repository.save(new Product(0, "The North Face", "Jacket", new BigDecimal("89.99"), "XL", "Green"));
            repository.save(new Product(0, "Nike", "Sneakers", new BigDecimal("79.99"), "42", "White"));
            repository.save(new Product(0, "Gucci", "Hat", new BigDecimal("44.99"), "One Size", "Red"));
            repository.save(new Product(0, "Puma", "Shorts", new BigDecimal("24.99"), "M", "Red"));
            repository.save(new Product(0, "Zara", "Blazer", new BigDecimal("119.99"), "L", "Blue"));
            repository.save(new Product(0, "Reebok", "Shoes", new BigDecimal("59.99"), "41", "Black"));
            repository.save(new Product(0, "Under Armour", "Shirt", new BigDecimal("39.99"), "M", "White"));
            repository.save(new Product(0, "Columbia", "Vest", new BigDecimal("69.99"), "L", "Green"));

            // Logging each preloaded product
            repository.findAll().forEach(product -> log.info("Preloaded: " + product));
        };
    }
}
