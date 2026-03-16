package com.pos.pos.branch.entity;

import com.pos.pos.store.entity.Store;
import com.pos.pos.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    private String address;
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Store store;


    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

     @PreUpdate
    public void onUpdate() {
    this.updatedAt = LocalDateTime.now();
    }





}
