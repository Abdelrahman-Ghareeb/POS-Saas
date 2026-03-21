package com.pos.pos.user.repo;

import com.pos.pos.store.entity.Store;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);


    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);

}
