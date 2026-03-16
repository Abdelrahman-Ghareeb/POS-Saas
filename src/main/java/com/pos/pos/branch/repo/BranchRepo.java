package com.pos.pos.branch.repo;

import com.pos.pos.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepo extends JpaRepository<Branch, Long> {

    List<Branch> findByStoreId(Long storeId);
}
