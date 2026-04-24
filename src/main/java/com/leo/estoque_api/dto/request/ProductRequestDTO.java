package com.leo.estoque_api.dto.request;

import java.math.BigDecimal;

public record ProductRequestDTO(
   String name,
   String description,
   BigDecimal price,
   Long quantity,
   Long providerId,
   Long categoryId,
   Boolean active
) {}
