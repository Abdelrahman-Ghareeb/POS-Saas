package com.pos.pos.category.mapper;

import com.pos.pos.category.dto.CategoryDto;
import com.pos.pos.category.entity.Category;

public class CategoryMapper {


   public static CategoryDto mapToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

   public static Category mapToEntity(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
