package com.pos.pos.invertory.entity;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int quantity;

    private LocalDateTime lastUpdated;



    @ManyToOne
    private Product product;
    @ManyToOne
    private Branch branch;

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
