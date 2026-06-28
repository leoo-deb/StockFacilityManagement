package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.product.ProductMapper;
import com.leo.estoque_api.exceptions.ProductNotFoundException;
import com.leo.estoque_api.dto.product.ProductRequestDTO;
import com.leo.estoque_api.dto.product.ProductResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.model.Stock;
import com.leo.estoque_api.repository.MovementRepository;
import com.leo.estoque_api.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final EntityManager entityManager;
    // TODO
    private MovementRepository movementsRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> listAllProducts() {
        return productMapper.toCollectionProductDTO(productRepository.findAll());
    }

    @Transactional
    public ProductResponseDTO registrationProduct(ProductRequestDTO dto) {
        Product product = productMapper.toProduct(dto);
        validateProduct(product, dto.categoryId());

        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product productCurrent = findById(id);
        entityManager.detach(productCurrent);

        productMapper.copyProductFromDto(dto, productCurrent);
        validateProduct(productCurrent, dto.categoryId());

        return productMapper.toProductDTO(productCurrent);
    }

    @Transactional
    public void toActiveProduct(Long id) {
        Product product = findById(id);
        product.toActive();
    }

    @Transactional
    public void toInactiveProduct(Long id) {
        Product product = findById(id);
        product.toInactive();
    }

    public ProductResponseDTO findDtoById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toProductDTO(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private void validateProduct(Product product, Long idCategory) {
        Optional<Product> productExist = productRepository.findByName(product.getName());

        if (productExist.isPresent() && !productExist.get().equals(product)) {
            throw new BusinessRuleException(String.format("Produto com nome %s já existe.", product.getName()));
        }

        Stock stock = product.getStock();
        stock.setUnitPrice(product.getPrice());
        stock.sumTotalPrice();
        stock.setProduct(product);
        product.setStock(stock);

        Category category = categoryService.findById(idCategory);
        product.setCategory(category);
    }

}
