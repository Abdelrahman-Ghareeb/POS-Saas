package com.pos.pos.invertory.service;

import com.pos.pos.invertory.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {

    InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;
    InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception;
    InventoryDTO getInventoryById(Long id) throws Exception;
    void deleteInventory(Long id);
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDTO> getInventoryByBranchId(Long branchId);
}
