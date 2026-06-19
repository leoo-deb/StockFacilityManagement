package com.leo.estoque_api.service;

import com.leo.estoque_api.exceptions.ResourceNotFoundException;
import com.leo.estoque_api.model.Category;
import com.leo.estoque_api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }

}
