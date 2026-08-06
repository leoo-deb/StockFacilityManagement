package com.leo.estoque_api.exceptions;

public class ProductVariantNotFoundException extends EntityNotFoundException {
    public ProductVariantNotFoundException(String sku) {
        super(String.format("Product variant with SKU: '%s' not found.", sku));
    }
}
