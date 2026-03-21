package com.pos.pos.user.entity;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.store.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;
    private String phone;

    private UserRole role;

    @ManyToOne
    private Store store;
    @ManyToOne
    private Branch branch;

    private String fullName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
}
