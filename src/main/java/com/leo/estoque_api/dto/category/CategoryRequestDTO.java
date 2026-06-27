package com.leo.estoque_api.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Nome é obrigatório.")
        String name
) {}
