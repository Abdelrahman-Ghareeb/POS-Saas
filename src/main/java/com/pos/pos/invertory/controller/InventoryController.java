package com.pos.pos.invertory.controller;


import com.pos.pos.invertory.dto.InventoryDTO;
import com.pos.pos.invertory.service.InventoryService;
import com.pos.pos.store.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        InventoryDTO createdInventory = inventoryService.createInventory(inventoryDTO);
        return ResponseEntity.ok(createdInventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id, @RequestBody InventoryDTO inventoryDTO) throws Exception {
        InventoryDTO updatedInventory = inventoryService.updateInventory(id, inventoryDTO);
        return ResponseEntity.ok(updatedInventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) throws Exception {
        InventoryDTO inventoryDTO = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) throws Exception {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Inventory Deleted Successfully").build());
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBranchId(@PathVariable Long branchId) throws Exception {
        List<InventoryDTO> inventoryDTO = inventoryService.getInventoryByBranchId(branchId);
        return ResponseEntity.ok(inventoryDTO);
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDTO> getInventoryByBranchIdAndProductId(@PathVariable Long branchId,@PathVariable Long productId) throws Exception {
        InventoryDTO inventoryDTO = inventoryService.getInventoryByProductIdAndBranchId(productId,branchId);
        return ResponseEntity.ok(inventoryDTO);
    }


}
