package com.leo.estoque_api.dto.mapper;

import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toProductDTO(Product product);
    Product toProduct(ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> toCollectionProductDTO(List<Product> products);

}
