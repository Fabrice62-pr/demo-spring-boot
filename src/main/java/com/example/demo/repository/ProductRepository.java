package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Recherche par nom (ignore la casse)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Recherche par fourchette de prix
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Recherche par quantité disponible
    List<Product> findByQuantityGreaterThan(Integer quantity);

    // Recherche par nom exact
    Optional<Product> findByNameIgnoreCase(String name);

    // Requête personnalisée pour les produits en stock
    @Query("SELECT p FROM Product p WHERE p.quantity > 0")
    List<Product> findProductsInStock();

    // Requête pour compter les produits par fourchette de prix
    @Query("SELECT COUNT(p) FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Long countProductsByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
}

