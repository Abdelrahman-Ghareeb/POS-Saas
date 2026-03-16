package com.pos.pos.category.controller;


import com.pos.pos.category.dto.CategoryDto;
import com.pos.pos.category.service.CategoryService;
import com.pos.pos.store.dto.response.ApiResponse;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception {
        // Implement the logic to create a new category
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<List<CategoryDto>> getAllCategories(@PathVariable long storeId) {
        // Implement the logic to retrieve all categories
        return ResponseEntity.ok(categoryService.listCategoriesByStoreId(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable long id,@RequestBody CategoryDto categoryDto) throws Exception {
        // Implement the logic to update an existing category
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable long id) throws Exception {
         // Implement the logic to delete a category
         categoryService.deleteCategory(id);
         return ResponseEntity.ok(ApiResponse.builder()
                         .message("Category deleted successfully")
                    .build());
     }
}
