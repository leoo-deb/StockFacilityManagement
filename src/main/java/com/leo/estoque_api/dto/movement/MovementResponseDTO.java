package com.leo.estoque_api.dto.movement;

import com.leo.estoque_api.model.User;

import java.time.OffsetDateTime;

public record MovementResponseDTO(
        Long id,
        String productVariantId,
        Long quantity,
        User user,
        OffsetDateTime dateTime,
        String type,
        String description
) {
}