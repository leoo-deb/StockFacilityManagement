package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.mapper.ProductMapper;
import com.leo.estoque_api.exceptions.ProductNotFoundException;
import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.repository.MovementsRepository;
import com.leo.estoque_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    // TODO
    private MovementsRepository movementsRepository;

    public List<ProductResponseDTO> listAllProducts() {
        return productMapper.toCollectionProductDTO(productRepository.findAll());
    }

    @Transactional
    public ProductResponseDTO registrationProduct(ProductRequestDTO dto) {
        Product product = productMapper.toProduct(dto);
        validateProduct(product, dto.categoryId());

        product.sumTotalPrice();
        product.setActive(Boolean.TRUE);

        return productMapper.toProductDTO(productRepository.save(product));
    }

    private void validateProduct(Product product, Long idCategory) {
        if (productRepository.existsByName(product.getName()))
            throw new BusinessRuleException(String.format("Produto com nome %s já existe.", product.getName()));

        Category category = categoryService.findById(idCategory);

        product.setCategory(category);
    }

    public ProductResponseDTO findDtoById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.toProductDTO(product);
    }
}
