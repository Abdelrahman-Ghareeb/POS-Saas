package com.pos.pos.branch.service;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.user.entity.User;

import java.util.List;

public interface BranchService {


    BranchDTO createBranch(BranchDTO branchDTO) throws Exception;
        BranchDTO getBranchById(Long id) throws Exception;

        BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception;

        void deleteBranch(Long id) throws Exception;

        List<BranchDTO> getAllBranchesByStoreId(long storeId);
}
