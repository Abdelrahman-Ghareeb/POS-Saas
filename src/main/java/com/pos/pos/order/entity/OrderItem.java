package com.pos.pos.order.entity;


import com.pos.pos.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double price;
    private Integer quantity;
    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}
