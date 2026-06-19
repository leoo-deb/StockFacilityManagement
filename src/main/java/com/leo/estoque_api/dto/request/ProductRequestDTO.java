package com.leo.estoque_api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(
   @NotBlank(message = "Nome não pode ser vazio.")
   String name,

   @NotBlank(message = "Descrição não pode ser vazia.")
   String description,

   @NotNull
   @PositiveOrZero
   BigDecimal price,

   @Positive
   Long quantity,

   @NotNull
   Long providerId,

   @NotNull
   Long categoryId
) {}
