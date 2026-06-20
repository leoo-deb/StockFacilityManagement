package com.leo.estoque_api.exceptions;

public abstract class ResourceNotFoundException extends BusinessRuleException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
