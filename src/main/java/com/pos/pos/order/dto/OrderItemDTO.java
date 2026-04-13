package com.pos.pos.order.dto;

import com.pos.pos.order.entity.Order;
import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.entity.Product;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {



    private Double price;
    private Integer quantity;

    private Long productId;
    private ProductDTO product;

    private Long orderId;
}
