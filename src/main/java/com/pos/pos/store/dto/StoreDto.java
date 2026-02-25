package com.pos.pos.store.dto;

import com.pos.pos.store.entity.StoreContent;
import com.pos.pos.store.entity.StoreStatus;
import com.pos.pos.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {

    private long id;
    private String brand;
    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;
    private String storeType;

    private StoreStatus storeStatus;
    private StoreContent storeContent = new StoreContent();

}
