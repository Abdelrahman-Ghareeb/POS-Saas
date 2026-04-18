package com.pos.pos.refund.service;

import com.pos.pos.refund.dto.RefundDTO;
import com.pos.pos.refund.entity.Refund;
import com.pos.pos.user.exception.UserException;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {


    RefundDTO createRefund(RefundDTO refund) throws Exception;
    List<RefundDTO> listAllRefunds();
    List<RefundDTO> getRefundByCashier(Long cashierId);
    List<RefundDTO> getRefundByShiftReports(Long ShiftReportId);
    List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId,LocalDateTime startDate,LocalDateTime endDate);
    List<RefundDTO> getRefundsByBranch(Long branchId);
    RefundDTO getRefundById(Long id);
    void deleteRefund(Long id);
}
