package com.leo.estoque_api.dto.product;

import com.leo.estoque_api.dto.category.CategoryResponseDTO;

import java.math.BigDecimal;

public record ProductResponseDTO(
  Long id,
  String name,
  String description,
  BigDecimal price,
  CategoryResponseDTO category
) {}
