package com.ecomweb.online.da.DA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecomweb.online.da.DA.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository interface for managing Product entities.
 * Extends JpaRepository and PagingAndSortingRepository to provide
 * CRUD and pagination functionality.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    /**
     * Retrieves a list of products filtered by color, ignoring case sensitivity.
     *
     * @param color The color to filter products by.
     * @return A list of products with the specified color.
     */
    List<Product> findByColorIgnoreCase(String color);

    /**
     * Custom query method to fetch all products by name, case-insensitive.
     *
     * @param name The name of the product to search for.
     * @return A list of products matching the specified name.
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    List<Product> fetchAllProductsWithName(@Param("name") String name);
}
