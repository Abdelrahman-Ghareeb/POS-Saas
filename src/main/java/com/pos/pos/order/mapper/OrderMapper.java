package com.pos.pos.order.mapper;

import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.entity.Order;
import com.pos.pos.user.mapper.UserMapper;

import java.util.stream.Collectors;

public class OrderMapper {


    public static OrderDto mapToDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .branchId(order.getBranch().getId())
                .totalAmount(order.getTotalAmount())
                .cashier(UserMapper.mapToDTO(order.getCashier()))
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .customer(order.getCustomer())
                .items(order.getItems().stream().map(OrderItemMapper::mapToDto).collect(Collectors.toList()))



                .build();

    }
}
