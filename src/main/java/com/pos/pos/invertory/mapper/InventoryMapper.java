package com.pos.pos.invertory.mapper;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.invertory.dto.InventoryDTO;
import com.pos.pos.invertory.entity.Inventory;
import com.pos.pos.product.entity.Product;
import com.pos.pos.product.mapper.ProductMapper;

public class InventoryMapper {

    public static InventoryDTO mapToDTO(Inventory inventory) {


        return InventoryDTO.builder()
                .name(inventory.getName())
                .quantity(inventory.getQuantity())
                .description(inventory.getDescription())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.mapToDTO(inventory.getProduct()))

                .build();
    }

    public static Inventory mapToEntity(InventoryDTO inventoryDTO, Product product, Branch branch) {
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .name(inventoryDTO.getName())
                .quantity(inventoryDTO.getQuantity())
                .description(inventoryDTO.getDescription())

                .build();
    }
}
