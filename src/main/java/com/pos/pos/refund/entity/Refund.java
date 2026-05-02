package com.pos.pos.refund.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pos.pos.branch.entity.Branch;
import com.pos.pos.order.entity.Order;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.shiftReport.entity.ShiftReport;
import com.pos.pos.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    private String reason;

    private Double amount;

    @ManyToOne
    @JsonIgnore
    private ShiftReport shiftReport;

    @ManyToOne
    private User cashier;

    @ManyToOne
    private Branch branch;

    private PaymentType paymentType;
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

