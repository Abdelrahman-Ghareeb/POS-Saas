package com.pos.pos.order.entity;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.customer.entity.Customer;
import com.pos.pos.order.enums.PaymentType;
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
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double totalAmount;
    private LocalDateTime createdAt;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private User cashier;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<OrderItem> items;

    private PaymentType paymentType;
}
