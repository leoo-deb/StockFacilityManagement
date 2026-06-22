package com.leo.estoque_api.dto.response;

import com.leo.estoque_api.model.User;

import java.time.OffsetDateTime;

public record MovementResponseDTO(
        Long id,
        ProductResponseDTO product,
        Long quantity,
        User user,
        OffsetDateTime dateTime,
        String type,
        String description
) {
}