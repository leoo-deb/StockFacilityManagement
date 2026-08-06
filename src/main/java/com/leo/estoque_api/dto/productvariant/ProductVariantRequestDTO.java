package com.leo.estoque_api.dto.productvariant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductVariantRequestDTO(
        @NotNull(message = "SKU is required.")
        String sku,

        @NotNull(message = "Model is required.")
        String model,

        @NotNull(message = "Stock's quantity is required.")
        @Positive(message = "You cannot enter a negative quantity.")
        Long stock,

        @NotNull(message = "Price is required.")
        @DecimalMin("0.01")
        BigDecimal price,

        String observation
) {
}
