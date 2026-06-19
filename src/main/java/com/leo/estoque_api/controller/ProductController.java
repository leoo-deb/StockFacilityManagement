package com.leo.estoque_api.controller;

import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.ResourceNotFoundException;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.repository.ProductRepository;
import com.leo.estoque_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable Long id) {
        return productService.findDtoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO save(@RequestBody ProductRequestDTO productRequestDTO) {

        try {
            return productService.registrationProduct(productRequestDTO);
        } catch (ResourceNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }

    }

}
