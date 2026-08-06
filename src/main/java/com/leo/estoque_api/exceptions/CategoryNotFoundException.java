package com.leo.estoque_api.exceptions;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Long id) {
        this(String.format("Code category '%d' was not found.", id));
    }
}
