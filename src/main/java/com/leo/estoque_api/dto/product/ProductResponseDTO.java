package com.leo.estoque_api.dto.product;

import com.leo.estoque_api.dto.category.CategoryResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(
  UUID id,
  String name,
  String description,
  Boolean active,
  CategoryResponseDTO category
) {}
