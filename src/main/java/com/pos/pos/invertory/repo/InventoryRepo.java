package com.pos.pos.invertory.repo;

import com.pos.pos.invertory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    List<Inventory> findByBranchId(Long branchId);
    Inventory findByProductIdAndBranchId(Long productId, Long branchId);

}
