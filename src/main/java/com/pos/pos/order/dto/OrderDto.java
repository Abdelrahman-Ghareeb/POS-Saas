package com.pos.pos.order.dto;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.branch.entity.Branch;
import com.pos.pos.customer.entity.Customer;
import com.pos.pos.order.entity.OrderItem;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto   {

    private long id;

    private Double totalAmount;
    private LocalDateTime createdAt;


    private Long branchId;
    private BranchDTO branch;

    private UserDto cashier;
    private Customer customer;
    private List<OrderItemDTO> items;

    private PaymentType paymentType;
}
