package com.leo.estoque_api.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long id) {
        this(String.format("Produto de código %d não foi encontrado.", id));
    }
}
