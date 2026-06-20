package com.leo.estoque_api.exceptions;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Long id) {
        this(String.format("Categoria de código %d não foi encontrada.", id));
    }
}
