package com.leo.estoque_api.repository;

import com.leo.estoque_api.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("FROM Stock s JOIN FETCH s.product")
    List<Stock> findAll();

    Optional<Stock> findByProductId(Long productId);

}
