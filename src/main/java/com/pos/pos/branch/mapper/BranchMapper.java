package com.pos.pos.branch.mapper;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.branch.entity.Branch;
import com.pos.pos.store.entity.Store;

import java.time.LocalDateTime;

public class BranchMapper {


    public static BranchDTO mapToDTO(Branch branch) {
        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .openingTime(branch.getOpeningTime())
                .closingTime(branch.getClosingTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .workingDays(branch.getWorkingDays())
                .storeId(branch.getStore()!= null?branch.getStore().getId():null)
                .build();
    }

    public static Branch mapToEntity(BranchDTO branchDTO, Store store) {
        return Branch.builder()
                .id(branchDTO.getId())
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .email(branchDTO.getEmail())
                .phone(branchDTO.getPhone())
                .openingTime(branchDTO.getOpeningTime())
                .closingTime(branchDTO.getClosingTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .workingDays(branchDTO.getWorkingDays())
                .store(store)

                .build();
    }
}
