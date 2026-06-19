package com.leo.estoque_api.service;

import com.leo.estoque_api.mapper.ProductMapper;
import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.ResourceNotFoundException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.model.Provider;
import com.leo.estoque_api.repository.MovementsRepository;
import com.leo.estoque_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProviderService providerService;
    private CategoryService categoryService;
    // TODO
    private MovementsRepository movementsRepository;

    private ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO registrationProduct(ProductRequestDTO dto) {
        Product product = productMapper.toProduct(dto);
        validateProduct(product);

        return productMapper.toProductDto(productRepository.save(product));
    }

    private void validateProduct(Product product) {
        if (productRepository.existsByName(product.getName()))
            throw new BusinessRuleException("This Product already exists.");

        Provider provider = providerService.findById(product.getProvider().getId());
        Category category = categoryService.findById(product.getCategory().getId());

        product.setProvider(provider);
        product.setCategory(category);
    }

    public ProductResponseDTO findDtoById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        return productMapper.toProductDto(product);
    }

}
