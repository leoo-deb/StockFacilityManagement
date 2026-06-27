package com.leo.estoque_api.dto.stock;

import java.math.BigDecimal;

public record StockResponseDTO(
        Long id,
        String productName,
        Long quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        String observation
) {
}
