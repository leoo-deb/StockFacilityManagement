package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.mapper.CategoryMapper;
import com.leo.estoque_api.dto.request.CategoryRequestDTO;
import com.leo.estoque_api.dto.response.CategoryResponseDTO;
import com.leo.estoque_api.exceptions.CategoryNotFoundException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper  categoryMapper;

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
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
