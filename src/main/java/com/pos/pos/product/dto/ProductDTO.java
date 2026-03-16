package com.pos.pos.product.dto;

import com.pos.pos.category.dto.CategoryDto;
import com.pos.pos.store.entity.Store;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {


    private long id;

    private String name;
    private String sku;
    private String description;


    private Double mrp;
    private Double sellingPrice;

    private String brand;
    private String image;

    private long storeId;
    private CategoryDto categoryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
