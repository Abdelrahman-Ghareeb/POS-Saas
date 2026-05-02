package com.pos.pos.shiftReport.entity;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.order.entity.Order;
import com.pos.pos.product.entity.Product;
import com.pos.pos.refund.entity.Refund;
import com.pos.pos.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;


    private Double totalSales;
    private Double totalRefunds;
    private int totalOrders;
    private Double netSales;


    @ManyToOne
    private User cashier;

    @ManyToOne
    private Branch branch;

    @Transient
   private  List<PaymentSummary> paymentSummaryList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> topSellingProducts;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> recentOrders;

    @OneToMany(mappedBy = "shiftReport")
    private List<Refund> returnsOrders;

}
