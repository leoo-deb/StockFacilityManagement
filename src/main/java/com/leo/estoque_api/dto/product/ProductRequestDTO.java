package com.leo.estoque_api.dto.product;

import com.leo.estoque_api.dto.stock.StockCreateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(
   @NotBlank(message = "Nome é obrigatório.")
   String name,

   @NotBlank(message = "Descrição é obrigatório.")
   String description,

   @NotNull(message = "Preço é obrigatório.")
   BigDecimal price,

   @NotNull(message = "Id da categoria é obrigatório.")
   Long categoryId,

   @NotNull(message = "Estoque é obrigatório")
   @Valid
   StockCreateDTO stock
) {}