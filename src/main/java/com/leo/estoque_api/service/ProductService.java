package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.mapper.ProductMapper;
import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.ResourceNotFoundException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.model.Provider;
import com.leo.estoque_api.repository.CategoryRepository;
import com.leo.estoque_api.repository.MovementsRepository;
import com.leo.estoque_api.repository.ProductRepository;
import com.leo.estoque_api.repository.ProviderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // TODO
    @Autowired
    private MovementsRepository movementsRepository;

    @Autowired
    private ProductMapper mapper;

    @Transactional
    public ProductResponseDTO registrationProduct(ProductRequestDTO dto) {

        if (productRepository.existsByName(dto.name()))
            throw new BusinessRuleException("This Product already exists.");

        Provider provider = providerRepository.findById(dto.providerId())
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found."));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        Product product = mapper.toProduct(dto, provider, category);
        product.setActive(true);

        return mapper.toProductResponseDTO(productRepository.save(product));
    }

    public ProductResponseDTO findById(Long id) {

        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        return mapper.toProductResponseDTO(product);

    }

}
