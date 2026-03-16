package com.pos.pos.branch.controller;

import com.pos.pos.branch.dto.BranchDTO;
import com.pos.pos.branch.service.BranchService;
import com.pos.pos.store.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws Exception {
        return ResponseEntity.ok(branchService.createBranch(branchDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) throws Exception {
        return ResponseEntity.ok(branchService.updateBranch(id, branchDTO));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchesByStoreId(@PathVariable Long storeId) throws Exception {

        return ResponseEntity.ok(branchService.getAllBranchesByStoreId(storeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Branch Deleted Successfully").build());
    }

}
