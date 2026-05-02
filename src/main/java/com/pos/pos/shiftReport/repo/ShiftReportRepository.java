package com.pos.pos.shiftReport.repo;

import com.pos.pos.shiftReport.entity.ShiftReport;
import com.pos.pos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport,Long> {

    List<ShiftReport> findByCashierId(Long cashierId);

    List<ShiftReport> findByBranchId(Long branchId);


    Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);
    Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier,LocalDateTime shiftStart,LocalDateTime shiftEnd);
}
