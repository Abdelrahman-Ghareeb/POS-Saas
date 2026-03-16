package com.pos.pos.product.mapper;

import com.pos.pos.category.entity.Category;
import com.pos.pos.category.mapper.CategoryMapper;
import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.entity.Product;
import com.pos.pos.store.entity.Store;

public class ProductMapper {


    public static ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sellingPrice(product.getSellingPrice())
                .sku(product.getSku())
                .brand(product.getBrand())
                .image(product.getImage())
                .categoryId(CategoryMapper.mapToDto(product.getCategory()))
                .storeId(product.getStore() != null ? product.getStore().getId() : null)
                .mrp(product.getMrp())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())


                .build();
    }

   public static Product mapToEntity(ProductDTO productDTO, Store store, Category category) {
        return Product.builder()
                .id(productDTO.getId())
                .store(store)
                .category(category)

                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .sellingPrice(productDTO.getSellingPrice())
                .sku(productDTO.getSku())
                .brand(productDTO.getBrand())
                .image(productDTO.getImage())
                .mrp(productDTO.getMrp())
                .createdAt(productDTO.getCreatedAt())
                .updatedAt(productDTO.getUpdatedAt())

                .build();
    }
}
