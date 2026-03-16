package com.pos.pos.branch.service.impl;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.branch.entity.Branch;
import com.pos.pos.branch.mapper.BranchMapper;
import com.pos.pos.branch.repo.BranchRepo;
import com.pos.pos.branch.service.BranchService;
import com.pos.pos.store.entity.Store;
import com.pos.pos.store.repo.StoreRepo;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepo branchRepository;
    private final StoreRepo storeRepo;
    private final UserService userService;
    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws Exception {


       User currentUser= userService.getCurrentUser();
        Store store = storeRepo.findByStoreAdminId(currentUser.getId());

         Branch branch = BranchMapper.mapToEntity(branchDTO,store);
        branchRepository.save(branch);


        return BranchMapper.mapToDTO(branch);
    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch existingBranch = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found with id: " + id));
        return BranchMapper.mapToDTO(existingBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception {
        User currentUser= userService.getCurrentUser();

        Branch existingBranch = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found with id: " + id));
        existingBranch.setName(branchDTO.getName());
        existingBranch.setEmail(branchDTO.getEmail());
        existingBranch.setPhone(branchDTO.getPhone());
        existingBranch.setAddress(branchDTO.getAddress());
        existingBranch.setWorkingDays(branchDTO.getWorkingDays());
        existingBranch.setOpeningTime(branchDTO.getOpeningTime());
        existingBranch.setClosingTime(branchDTO.getClosingTime());
        existingBranch.setUpdatedAt(LocalDateTime.now());
        existingBranch.setManager(currentUser);

        Branch branch=branchRepository.save(existingBranch);
        return BranchMapper.mapToDTO(branch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existingBranch = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found with id: " + id));

        branchRepository.delete(existingBranch);

    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(long storeId) {
        List<Branch> branches = branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::mapToDTO).collect(Collectors.toList());

    }
}
