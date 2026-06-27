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
        validateEntryStock(stock, dto.productId());

        stock.registerEntry(dto.quantity());
        stock.sumTotalPrice();
        return stockMapper.toStockDTO(stock);
    }

    @Transactional
    public StockResponseDTO registerExit(StockRequestDTO dto) {
        Stock stock = findByProduct(dto.productId());
        validateExitStock(stock, dto.productId(), dto.quantity());

        stock.registerExit(dto.quantity());
        stock.sumTotalPrice();
        return stockMapper.toStockDTO(stock);
    }

    public Stock findByProduct(Long productId) {
        return stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException(productId));
    }

    private void validateEntryStock(Stock stock, Long productId) {
        Product product = productService.findById(productId);

        if (!product.isActive()) {
            throw new BusinessRuleException(String.format("Não é possível registrar uma " +
                    "entrada com o produto de código %d, pois está inativo.", productId));
        }

        stock.setProduct(product);
    }

    private void validateExitStock(Stock stock, Long productId, Long quantity) {
        Product product = productService.findById(productId);

        if (!product.isActive()) {
            throw new BusinessRuleException(String.format("Não é possível registrar uma " +
                    "entrada com o produto de código %d, pois está inativo.", productId));
        }

        if (stock.getQuantity() < quantity) {
            throw new BusinessRuleException(String.format("Estoque do produto de código %d não possui quantidade " +
                    "suficiente para registrar uma saída. Quantidade atual: %d", productId, stock.getQuantity()));
        }

        stock.setProduct(product);
    }

}
