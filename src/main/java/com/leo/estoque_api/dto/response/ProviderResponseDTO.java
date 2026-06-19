package com.leo.estoque_api.dto.response;

public record ProviderResponseDTO(
   Long id,
   String name,
   String cnpj,
   String email,
   String contact
) {}
