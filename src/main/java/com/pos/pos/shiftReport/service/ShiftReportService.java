package com.pos.pos.shiftReport.service;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.shiftReport.dto.ShiftReportDTO;
import com.pos.pos.shiftReport.entity.ShiftReport;
import com.pos.pos.user.exception.UserException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDTO startShift() throws Exception;

    ShiftReportDTO endShift(Long shiftId,LocalDateTime endShift) throws Exception;

    ShiftReportDTO getShiftReportById(Long shiftReportId) throws Exception;
    List<ShiftReportDTO> getAllShiftReports();
    List<ShiftReportDTO> getAllShiftReportsByBranchIs(Long branchId);
    List<ShiftReportDTO> getAllShiftReportsByCashierId(Long cashierId);

    ShiftReportDTO getCurrentShiftInProgress(Long cashierId) throws Exception;
    ShiftReportDTO getShiftByCashierANdDate(Long cashierId,LocalDateTime dateTime) throws Exception;

}
