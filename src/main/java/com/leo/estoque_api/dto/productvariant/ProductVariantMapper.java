package com.leo.estoque_api.dto.productvariant;

import com.leo.estoque_api.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    ProductVariant toProductVariant(ProductVariantRequestDTO productVariantRequestDTO);

    ProductVariantResponseDTO toProductVariantDTO(ProductVariant productVariant);

    List<ProductVariantResponseDTO> toCollectionProductVariantDTO(List<ProductVariant> productVariants);

}
