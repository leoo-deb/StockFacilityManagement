package com.leo.estoque_api.repository;

import com.leo.estoque_api.model.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, UUID> {

    @Query("FROM ProductVariant s JOIN FETCH s.product")
    List<ProductVariant> findAll();

    Page<ProductVariant> findAllByProductId(UUID productId, Pageable pageable);

    Optional<ProductVariant> findBySku(String sku);

}
