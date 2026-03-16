package com.pos.pos.branch.dto;

import com.pos.pos.store.dto.StoreDto;
import com.pos.pos.store.entity.Store;
import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder

public class BranchDTO {

    private long id;
    private String name;
    private String phone;
    private String address;
    private String email;

    private List<String> workingDays;

    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private StoreDto store;

    private long storeId;


    private UserDto manager;
}
