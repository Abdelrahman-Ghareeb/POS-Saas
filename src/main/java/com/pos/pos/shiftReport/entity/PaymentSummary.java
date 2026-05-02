package com.pos.pos.shiftReport.entity;

import com.pos.pos.order.enums.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {

    private PaymentType paymentType;
    private Double totalAmount;
    private int transactionCount;
    private Double percentage;
}
