package com.leo.estoque_api.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockRequestDTO(
        @NotNull(message = "productId é obrigatório")
        Long productId,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Não é possível fazer um registro de uma quatidade negativa")
        Long quantity,

        String observation
) {}
