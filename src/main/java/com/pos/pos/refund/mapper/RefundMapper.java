package com.pos.pos.refund.mapper;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.branch.mapper.BranchMapper;
import com.pos.pos.refund.dto.RefundDTO;
import com.pos.pos.refund.entity.Refund;

public class RefundMapper {

    public static RefundDTO mapToDTO(Refund refund){

        return RefundDTO.builder()
                .id(refund.getId())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branch(BranchMapper.mapToDTO(refund.getBranch()))
                .reason(refund.getReason())
                .paymentType(refund.getPaymentType())
                .orderId(refund.getOrder().getId())
                .branchId(refund.getBranch().getId())
                .shiftReportId(refund.getShiftReport() != null ?refund.getShiftReport().getId():null)
                .createdAt(refund.getCreatedAt())



                .build();
    }
}
