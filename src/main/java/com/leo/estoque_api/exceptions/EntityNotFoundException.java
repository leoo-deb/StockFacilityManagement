package com.leo.estoque_api.exceptions;

public abstract class EntityNotFoundException extends BusinessRuleException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
