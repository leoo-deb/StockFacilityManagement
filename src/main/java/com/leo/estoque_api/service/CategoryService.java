package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.mapper.CategoryMapper;
import com.leo.estoque_api.dto.request.CategoryRequestDTO;
import com.leo.estoque_api.dto.response.CategoryResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.CategoryNotFoundException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final EntityManager entityManager;

    public List<CategoryResponseDTO> listAllCategories() {
        return categoryMapper.toCollectionCategoryDTO(categoryRepository.findAll());
    }

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        validateCategory(category);
        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category category = findById(id);
        entityManager.detach(category);

        categoryMapper.copyCategoryFromDto(dto, category);
        validateCategory(category);

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

    private void validateCategory(Category category) {
        Optional<Category> categoryExist = categoryRepository.findByName(category.getName());

        if (categoryExist.isPresent() && !categoryExist.get().equals(category)) {
            throw new BusinessRuleException(String.format("Categoria com nome %s já existe.", category.getName()));
        }
    }

}
