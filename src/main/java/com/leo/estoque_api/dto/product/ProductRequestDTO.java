package com.leo.estoque_api.dto.product;

import jakarta.validation.constraints.*;

public record ProductRequestDTO(
   @NotNull(message = "categoryId is required.")
   Long categoryId,

   @NotBlank(message = "Name is required.")
   String name,

   @NotBlank(message = "Description is required.")
   String description

) {}