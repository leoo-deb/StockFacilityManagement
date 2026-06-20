package com.leo.estoque_api.dto.mapper;

import com.leo.estoque_api.dto.request.CategoryRequestDTO;
import com.leo.estoque_api.dto.request.ProductRequestDTO;
import com.leo.estoque_api.dto.response.CategoryResponseDTO;
import com.leo.estoque_api.dto.response.ProductResponseDTO;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toCategoryDTO(Category category);

    Category toCategory(CategoryRequestDTO categoryRequestDTO);

    List<CategoryResponseDTO> toCollectionCategoryDTO(List<Category> categories);

    void copyCategoryFromDto(CategoryRequestDTO categoryRequestDTO, @MappingTarget Category category);

}
