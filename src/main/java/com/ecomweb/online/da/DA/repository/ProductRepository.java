package com.ecomweb.online.da.DA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecomweb.online.da.DA.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query method to fetch products by color
    List<Product> findByColorIgnoreCase(String color);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    List<Product> fetchAllProductsWithName(@Param("name") String name);
}
