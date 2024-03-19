package com.market.marketJpa.repository;

import com.market.marketJpa.vo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.seq = :seq")
    Optional<Product> findProductDetailsById(@Param("seq") Integer seq);
}