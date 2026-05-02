package com.pos.pos.shiftReport.dto;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.entity.Order;
import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.entity.Product;
import com.pos.pos.refund.dto.RefundDTO;
import com.pos.pos.refund.entity.Refund;
import com.pos.pos.shiftReport.entity.PaymentSummary;
import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ShiftReportDTO {


    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;


    private Double totalSales;
    private Double totalRefunds;
    private int totalOrders;
    private Double netSales;


    private UserDto cashier;

    private BranchDTO branch;

    private Long cashierId;
    private Long branchId;

    private List<PaymentSummary> paymentSummaryList;

    private List<ProductDTO> topSellingProducts;


    private List<OrderDto> recentOrders;

    private List<RefundDTO> returnsOrders;
}
