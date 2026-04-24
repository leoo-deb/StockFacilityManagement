package com.leo.estoque_api.dto.mapper;

import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.model.Product;
import com.leo.estoque_api.model.Provider;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO toProductResponseDTO(Product product) {

        if (product == null) return null;

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getProvider().getName(),
                product.getProvider().getId(),
                product.getCategory().getName(),
                product.getCategory().getId(),
                product.getActive()
                );

    }

    public Product toProduct(ProductRequestDTO productRequestDTO, Provider provider, Category category) {

        if (productRequestDTO == null) return null;

        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setDescription(productRequestDTO.description());
        product.setPrice(productRequestDTO.price());
        product.setQuantity(productRequestDTO.quantity());
        product.setProvider(provider);
        product.setCategory(category);
        product.setActive(productRequestDTO.active());

        return product;

    }

}
