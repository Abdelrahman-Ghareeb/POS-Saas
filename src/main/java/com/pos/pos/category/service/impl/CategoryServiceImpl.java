package com.pos.pos.category.service.impl;

import com.pos.pos.category.dto.CategoryDto;
import com.pos.pos.category.entity.Category;
import com.pos.pos.category.mapper.CategoryMapper;
import com.pos.pos.category.repo.CategoryRepo;
import com.pos.pos.category.service.CategoryService;
import com.pos.pos.store.entity.Store;
import com.pos.pos.store.repo.StoreRepo;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.entity.UserRole;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final UserService userService;
    private final StoreRepo storeRepo;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {

        User user = userService.getCurrentUser();

        Store store = storeRepo.findById(categoryDto.getStoreId())
                .orElseThrow(() -> new Exception("Store not found with id: " + categoryDto.getStoreId()));

        Category category = Category.builder()
                .name(categoryDto.getName())
                .store(store)
                .build();
        checkUserPermissionForStore(user,category.getStore());
        return CategoryMapper.mapToDto(categoryRepo.save(category));
    }

    @Override
    public List<CategoryDto> listCategoriesByStoreId(long storeId) {

        List<Category> categories = categoryRepo.findByStoreId(storeId);

        return categories.stream().map(CategoryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) throws Exception {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new Exception("Category not found with id: " + categoryId));

        category.setName(category.getName());
        User user = userService.getCurrentUser();
        checkUserPermissionForStore(user,category.getStore());
        return CategoryMapper.mapToDto(categoryRepo.save(category));
    }

    @Override
    public void deleteCategory(long categoryId) throws Exception {

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new Exception("Category not found with id: " + categoryId));
;
        User user = userService.getCurrentUser();
        checkUserPermissionForStore(user,category.getStore());

        categoryRepo.delete(category);

    }


    public void checkUserPermissionForStore(User user, Store store) throws Exception {

        boolean isAdmin = user.getRole().equals(UserRole.ROLE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin  && isSameStore) && !isManager) {
            throw new Exception("User does not have permission to access this store.");
        }

    }

}
