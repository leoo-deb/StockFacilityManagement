package com.leo.estoque_api.dto.movement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record MovementRequestDTO(
        @NotNull(message = "variantId is required.")
        UUID variantId,

        @NotNull(message = "Type is required.")
        String type,

        @NotNull(message = "Quantity is required")
        @Positive(message = "Não é possível fazer um registro de uma quatidade negativa")
        Long quantity,

        String description
) {
}
