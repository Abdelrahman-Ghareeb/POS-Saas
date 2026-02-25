package com.pos.pos.store.mapper;

import com.pos.pos.store.dto.StoreDto;
import com.pos.pos.store.entity.Store;
import com.pos.pos.user.entity.User;

public class StoreMapper {


    public static StoreDto mapToDTO(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .brand(store.getBrand())
                .storeAdmin(store.getStoreAdmin())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .description(store.getDescription())
                .storeType(store.getStoreType())
                .storeStatus(store.getStoreStatus())
                .storeContent(store.getStoreContent())


                .build();
    }


   public static Store mapToEntity(StoreDto storeDto, User storeAdmin) {
        return Store.builder()
                .id(storeDto.getId())
                .brand(storeDto.getBrand())
                .storeAdmin(storeDto.getStoreAdmin())
                .createdAt(storeDto.getCreatedAt())
                .storeAdmin(storeAdmin)
                .updatedAt(storeDto.getUpdatedAt())
                .description(storeDto.getDescription())
                .storeType(storeDto.getStoreType())
                .storeStatus(storeDto.getStoreStatus())
                .storeContent(storeDto.getStoreContent())


                .build();
    }
}
