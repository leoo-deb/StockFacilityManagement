package com.leo.estoque_api.controller;

import com.leo.estoque_api.dto.stock.StockRequestDTO;
import com.leo.estoque_api.dto.stock.StockResponseDTO;
import com.leo.estoque_api.model.Stock;
import com.leo.estoque_api.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> listAll() {
        return ResponseEntity.ok(stockService.listAllStock());
    }

    @PutMapping
    public ResponseEntity<StockResponseDTO> registerEntry(@RequestBody @Valid StockRequestDTO stockRequestDTO) {
        StockResponseDTO stock = stockService.registerEntry(stockRequestDTO);
        return ResponseEntity.ok(stock);
    }

    @DeleteMapping
    public ResponseEntity<StockResponseDTO> registerExit(@RequestBody @Valid StockRequestDTO stockRequestDTO) {
        StockResponseDTO stock = stockService.registerExit(stockRequestDTO);
        return ResponseEntity.ok(stock);
    }

}
