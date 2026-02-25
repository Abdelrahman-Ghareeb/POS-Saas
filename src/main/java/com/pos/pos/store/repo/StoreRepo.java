package com.pos.pos.store.repo;

import com.pos.pos.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store,Long> {
    Store findByStoreAdminId(Long id);
}
