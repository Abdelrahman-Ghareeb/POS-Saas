package com.pos.pos.order.repo;

import com.pos.pos.order.entity.Order;
import com.pos.pos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {


    List<Order> findByCashierId(Long id);


    List<Order> findByCustomerId(Long id);
    List<Order> findByBranchId(Long id);
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime from,LocalDateTime to);
    List<Order> findByCashierAndCreatedAtBetween(User cashier,LocalDateTime from,LocalDateTime to);
    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(long branchId);
}
