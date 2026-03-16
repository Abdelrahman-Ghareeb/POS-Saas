package com.pos.pos.category.repo;

import com.pos.pos.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findByStoreId(Long storeId);
}
