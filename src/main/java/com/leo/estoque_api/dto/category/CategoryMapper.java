package com.leo.estoque_api.dto.category;

import com.leo.estoque_api.model.Category;
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
