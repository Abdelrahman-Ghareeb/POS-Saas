package com.pos.pos.shiftReport.mapper;

import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.entity.Order;
import com.pos.pos.order.mapper.OrderMapper;
import com.pos.pos.product.dto.ProductDTO;
import com.pos.pos.product.entity.Product;
import com.pos.pos.product.mapper.ProductMapper;
import com.pos.pos.refund.dto.RefundDTO;
import com.pos.pos.refund.entity.Refund;
import com.pos.pos.refund.mapper.RefundMapper;
import com.pos.pos.shiftReport.dto.ShiftReportDTO;
import com.pos.pos.shiftReport.entity.ShiftReport;
import com.pos.pos.user.mapper.UserMapper;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {


    public static ShiftReportDTO mapToDto(ShiftReport shiftReport){
        return ShiftReportDTO.builder()
                .id(shiftReport.getId())
                .shiftStart(shiftReport.getShiftStart())
                .shiftEnd(shiftReport.getShiftEnd())
                .totalSales(shiftReport.getTotalSales())
                .totalRefunds(shiftReport.getTotalRefunds())
                .totalOrders(shiftReport.getTotalOrders())
                .netSales(shiftReport.getNetSales())
                .cashier(UserMapper.mapToDTO(shiftReport.getCashier()))
                .cashierId(shiftReport.getCashier().getId())
                .branchId(shiftReport.getBranch().getId())
                .recentOrders(mapOrders(shiftReport.getRecentOrders()))
                .returnsOrders(mapRefund(shiftReport.getReturnsOrders()))
                .topSellingProducts(mapSellingProduct(shiftReport.getTopSellingProducts()))
                .paymentSummaryList(shiftReport.getPaymentSummaryList())

                .build();
    }

    private static List<ProductDTO> mapSellingProduct(List<Product> topSellingProducts) {
        if(topSellingProducts==null || topSellingProducts.isEmpty()){return null;}
        return topSellingProducts.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());


    }

    private static List<RefundDTO> mapRefund(List<Refund> returnsOrders) {
        return returnsOrders.stream().map(RefundMapper::mapToDTO).collect(Collectors.toList());
    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {

        if(recentOrders==null || recentOrders.isEmpty()){return null;}
        return recentOrders.stream().map(OrderMapper::mapToDto).collect(Collectors.toList());
    }
}
