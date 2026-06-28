package com.leo.estoque_api.controller;

import com.leo.estoque_api.dto.product.ProductRequestDTO;
import com.leo.estoque_api.dto.product.ProductResponseDTO;
import com.leo.estoque_api.dto.stock.StockMapper;
import com.leo.estoque_api.dto.stock.StockResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.CategoryNotFoundException;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockMapper stockMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.findDtoById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<StockResponseDTO> findByStock(@PathVariable Long id) {
        Product product = productService.findById(id);
        StockResponseDTO stockResponseDTO = stockMapper.toStockDTO(product.getStock());
        return ResponseEntity.ok(stockResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(productService.registrationProduct(productRequestDTO));
        } catch (CategoryNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        try {
            ProductResponseDTO productResponseDTO = productService.updateProduct(id, productRequestDTO);
            return ResponseEntity.ok(productResponseDTO);
        } catch (CategoryNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}/activation")
    public ResponseEntity<Void> activation(@PathVariable Long id) {
        productService.toActiveProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/activation")
    public ResponseEntity<Void> deactivation(@PathVariable Long id) {
        productService.toInactiveProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
