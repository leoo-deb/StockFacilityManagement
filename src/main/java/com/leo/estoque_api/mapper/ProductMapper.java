package com.leo.estoque_api.mapper;

import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.model.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ProductMapper {

    private ModelMapper modelMapper;

    public ProductResponseDTO toProductDto(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public Product toProduct(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }

}
