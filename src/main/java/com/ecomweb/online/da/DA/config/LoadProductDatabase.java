package com.ecomweb.online.da.DA.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.repository.ProductRepository;

@Configuration
public class LoadProductDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            repository.save(new Product(0, "Adidas", "T-Shirt", 29.99, "M", "Black"));
            repository.save(new Product(0, "Levi", "Jeans", 49.99, "L", "Blue"));
            repository.save(new Product(0, "The North Face", "Jacket", 89.99, "XL", "Green"));
            repository.save(new Product(0, "Nike", "Sneakers", 79.99, "42", "White"));
            repository.save(new Product(0, "Gucci", "Hat", 44.99, "One Size", "Red"));
            repository.save(new Product(0, "Puma", "Shorts", 24.99, "M", "Red"));
            repository.save(new Product(0, "Zara", "Blazer", 119.99, "L", "Blue"));
            repository.save(new Product(0, "Reebok", "Shoes", 59.99, "41", "Black"));
            repository.save(new Product(0, "Under Armour", "Shirt", 39.99, "M", "White"));
            repository.save(new Product(0, "Columbia", "Vest", 69.99, "L", "Green"));

            repository.findAll().forEach(product -> log.info("Preloaded: " + product));
        };





    }
}
