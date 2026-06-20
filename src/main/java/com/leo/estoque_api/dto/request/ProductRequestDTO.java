package com.leo.estoque_api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(
   @NotBlank(message = "Nome não pode ser vazio.")
   String name,

   @NotBlank(message = "Descrição não pode ser vazia.")
   String description,

   @NotNull(message = "Preço não pode ser vazio.")
   @PositiveOrZero(message = "Preço deve ser maior ou igual à zero.")
   BigDecimal price,

   @NotNull(message = "Quantidade não pode ser vazia.")
   @Positive(message = "Quantidade deve ser maior que zero.")
   Long quantity,

   @NotNull(message = "Id da categoria não pode ser vazia.")
   Long categoryId
) {}