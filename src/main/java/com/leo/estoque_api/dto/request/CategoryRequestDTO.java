package com.leo.estoque_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
        @NotBlank(message = "Nome não pode ser vazio.")
        String name
) {}
