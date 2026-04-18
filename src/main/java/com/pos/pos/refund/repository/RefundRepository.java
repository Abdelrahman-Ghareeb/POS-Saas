package com.pos.pos.refund.repository;

import com.pos.pos.refund.entity.Refund;
import com.pos.pos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository  extends JpaRepository<Refund,Long> {


    List<Refund> findByCashierIdAndCreatedAtBetween(Long cashierId, LocalDateTime from, LocalDateTime to);
    List<Refund> findByCashierId(Long cashierId);
    List<Refund> findByShiftReportId(Long id);
    List<Refund> findByBranchId(Long branchId);
}
