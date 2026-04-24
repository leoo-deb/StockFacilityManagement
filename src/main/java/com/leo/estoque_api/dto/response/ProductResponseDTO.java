package com.leo.estoque_api.dto.response;

import java.math.BigDecimal;

public record ProductResponseDTO(
  Long id,
  String name,
  String description,
  BigDecimal price,
  Long quantity,
  String nameProvider,
  Long providerId,
  String nameCategory,
  Long categoryId,
  Boolean active
) {}
