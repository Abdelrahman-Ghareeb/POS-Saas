package com.pos.pos.invertory.service.impl;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.branch.repo.BranchRepo;
import com.pos.pos.invertory.dto.InventoryDTO;
import com.pos.pos.invertory.entity.Inventory;
import com.pos.pos.invertory.mapper.InventoryMapper;
import com.pos.pos.invertory.repo.InventoryRepo;
import com.pos.pos.invertory.service.InventoryService;
import com.pos.pos.product.entity.Product;
import com.pos.pos.product.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventortServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final BranchRepo branchRepo;
    private final ProductRepo productRepo;
    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {

        Branch branch = branchRepo.findById(inventoryDTO.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found with id: " + inventoryDTO.getBranchId()));
        Product product = productRepo.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new Exception("Product not found with id: " + inventoryDTO.getProductId()));

        Inventory inventory = InventoryMapper.mapToEntity(inventoryDTO, product, branch);
        Inventory savedInventory = inventoryRepo.save(inventory);

        return InventoryMapper.mapToDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception {

        Inventory inventory = inventoryRepo.findById(id).orElseThrow(()
                -> new Exception("Inventory not found with id: " + id));

        inventory.setQuantity(inventoryDTO.getQuantity());
        Inventory updatedInventory = inventoryRepo.save(inventory);
        return InventoryMapper.mapToDTO(updatedInventory);
    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(()
                -> new Exception("Inventory not found with id: " + id));
        return InventoryMapper.mapToDTO(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepo.deleteById(id);

    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {

        Inventory inventory = inventoryRepo.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.mapToDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getInventoryByBranchId(Long branchId) {

        List<Inventory> inventories = inventoryRepo.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::mapToDTO).collect(Collectors.toList());
    }
}
