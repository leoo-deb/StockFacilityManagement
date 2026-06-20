package com.leo.estoque_api.dto.mapper;

import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductResponseDTO toProductDTO(Product product);

    @Mapping(target = "category", ignore = true)
    Product toProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> toCollectionProductDTO(List<Product> products);

    void copyProductFromDto(ProductRequestDTO productRequestDTO, @MappingTarget Product product);

}
