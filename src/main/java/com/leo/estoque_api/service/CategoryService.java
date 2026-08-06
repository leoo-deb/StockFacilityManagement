package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.category.CategoryMapper;
import com.leo.estoque_api.dto.category.CategoryRequestDTO;
import com.leo.estoque_api.dto.category.CategoryResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.CategoryNotFoundException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> listAllCategories() {
        return categoryMapper.toCollectionCategoryDTO(categoryRepository.findAll());
    }

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        if (categoryRepository.existsByNameIgnoreCase(dto.name())) {
            throw new BusinessRuleException(String.format("Category with name: '%s' already exists", dto.name()));
        }

        Category category = categoryMapper.toCategory(dto);
        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category category = findById(id);

        if (!category.getName().equalsIgnoreCase(dto.name())
                && categoryRepository.existsByNameIgnoreCase(dto.name())) {
            throw new BusinessRuleException(String.format("Category with name: '%s' already exists.", dto.name()));
        }

        categoryMapper.copyCategoryFromDto(dto, category);
        return categoryMapper.toCategoryDTO(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public CategoryResponseDTO findDtoById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.toCategoryDTO(category);
    }

}
