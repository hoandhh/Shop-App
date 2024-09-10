package com.hoandhh.backend.services;

import com.hoandhh.backend.dtos.CategoryDTO;
import com.hoandhh.backend.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category updateCategory(long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(long id);
}
