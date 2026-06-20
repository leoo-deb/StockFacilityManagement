package com.leo.estoque_api.repository;

import com.leo.estoque_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p JOIN FETCH p.category")
    List<Product> findAll();

    Boolean existsByName(String name);

}
