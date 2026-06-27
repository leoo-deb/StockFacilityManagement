package com.leo.estoque_api.exceptions;

public class StockNotFoundException extends EntityNotFoundException {
    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException(Long productId) {
        this(String.format("Estoque do produto de código %d não foi encontrado.", productId));
    }
}
