package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.stock.StockMapper;
import com.leo.estoque_api.dto.stock.StockRequestDTO;
import com.leo.estoque_api.dto.stock.StockResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.StockNotFoundException;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.model.Stock;
import com.leo.estoque_api.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<StockResponseDTO> listAllStock() {
        return stockMapper.toCollectionStockDTO(stockRepository.findAll());
    }

    @Transactional
    public StockResponseDTO registerEntry(StockRequestDTO dto) {
        Stock stock = findByProduct(dto.productId());
        Product product = productService.findById(dto.productId());
        product.isActive();

        stock.registerEntry(dto.quantity());
        stock.sumTotalPrice();
        return stockMapper.toStockDTO(stock);
    }

    @Transactional
    public StockResponseDTO registerExit(StockRequestDTO dto) {
        Stock stock = findByProduct(dto.productId());
        Product product = productService.findById(dto.productId());
        product.isActive();

        if (stock.getQuantity() < dto.quantity()) {
            throw new BusinessRuleException(String.format("Estoque do produto de código %d não possui quantidade " +
                    "suficiente para registrar uma saída. Quantidade atual: %d", dto.productId(), stock.getQuantity()));
        }

        stock.registerExit(dto.quantity());
        stock.sumTotalPrice();
        return stockMapper.toStockDTO(stock);
    }

    public Stock findByProduct(Long productId) {
        return stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException(productId));
    }

}
