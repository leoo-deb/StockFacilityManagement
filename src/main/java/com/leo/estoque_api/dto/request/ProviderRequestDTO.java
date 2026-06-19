package com.leo.estoque_api.dto.request;

public record ProviderRequestDTO(
        String name,
        String cnpj,
        String email,
        String contact
) {}
