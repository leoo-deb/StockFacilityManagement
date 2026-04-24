package com.leo.estoque_api.exceptions;

public class ResourceNotFoundException extends BusinessRuleException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
