package com.wcs.blog4.service;

import com.wcs.blog4.dto.CategoryDTO;
import com.wcs.blog4.mapper.CategoryMapper;
import com.wcs.blog4.model.Category;
import com.wcs.blog4.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    public Optional<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(categoryDTO.getName());
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toDTO(updatedCategory);
        });
    }

    public boolean deleteCategory(Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
