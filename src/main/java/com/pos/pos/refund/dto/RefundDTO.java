package com.pos.pos.refund.dto;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.user.dtos.UserDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RefundDTO {

    private Long id;

    private OrderDto order;
    private Long orderId;

    private String reason;

    private double amount;

//    private ShiftRepost shiftRepost;
    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;

    private BranchDTO branch;
    private Long branchId;

    private PaymentType paymentType;
    private LocalDateTime createdAt;

}
