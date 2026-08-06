package com.leo.estoque_api.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public  ProductNotFoundException(UUID id) {
        this(String.format("Code product '%s' was not found.", id));
    }
}
