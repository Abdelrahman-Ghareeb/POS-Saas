package com.pos.pos.store.entity;

import com.pos.pos.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stores")
@Setter
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String brand;
    @OneToOne
    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;
    private String storeType;

    private StoreStatus storeStatus;
    @Embedded
    private StoreContent storeContent = new StoreContent();


    @PrePersist
    public  void  onCreate(){
        this.createdAt = LocalDateTime.now();
       this.storeStatus = StoreStatus.PENDING;
    }

    @PreUpdate
    public  void  onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


}
