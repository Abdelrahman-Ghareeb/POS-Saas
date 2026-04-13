package com.pos.pos.order.mapper;

import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.dto.OrderItemDTO;
import com.pos.pos.order.entity.OrderItem;
import com.pos.pos.product.mapper.ProductMapper;

public class OrderItemMapper {

    public static OrderItemDTO mapToDto(OrderItem orderItem){

        return OrderItemDTO.builder()
                .orderId(orderItem.getOrder().getId())
                .price(orderItem.getPrice())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .product(ProductMapper.mapToDTO(orderItem.getProduct()))


                .build();
    }
}
