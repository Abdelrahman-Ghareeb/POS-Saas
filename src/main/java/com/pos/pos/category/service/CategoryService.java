package com.pos.pos.category.service;

import com.pos.pos.category.dto.CategoryDto;
import com.pos.pos.user.exception.UserException;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    public List<CategoryDto> listCategoriesByStoreId(long storeId);
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) throws Exception;
    public void deleteCategory(long categoryId) throws Exception;
}
