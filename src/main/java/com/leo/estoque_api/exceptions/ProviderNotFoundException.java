package com.leo.estoque_api.exceptions;

public class ProviderNotFoundException extends EntityNotFoundException {
    public ProviderNotFoundException(String message) {
        super(message);
    }

    public ProviderNotFoundException(Long id) {
        this(String.format("Empresa de código %d não foi encontrada.", id));
    }
}
