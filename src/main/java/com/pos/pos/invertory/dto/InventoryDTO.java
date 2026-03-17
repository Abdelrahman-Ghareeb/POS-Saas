package com.pos.pos.invertory.dto;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.branch.entity.Branch;
import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.entity.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class InventoryDTO {


    private long id;
    private String name;
    private String description;
    private int quantity;

    private long productId;
    private long branchId;


    private ProductDTO product;
    private BranchDTO branch;

}
