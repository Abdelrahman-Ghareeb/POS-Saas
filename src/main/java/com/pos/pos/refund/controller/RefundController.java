package com.pos.pos.refund.controller;

import com.pos.pos.refund.dto.RefundDTO;
import com.pos.pos.refund.service.RefundService;
import com.pos.pos.store.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/refund")
@RequiredArgsConstructor
public class RefundController {


    private final RefundService refundService;


    @PostMapping()
    public ResponseEntity<RefundDTO> createRefund(@RequestBody RefundDTO refundDTO) throws Exception {
        return  ResponseEntity.ok(refundService.createRefund(refundDTO));
    }

    @GetMapping
    public ResponseEntity<List<RefundDTO>> getAllRefunds() throws Exception {
        return  ResponseEntity.ok(refundService.listAllRefunds());
    }

    @GetMapping("refunds/{cashierId}")
    public ResponseEntity<List<RefundDTO>> getAllRefundsByCashier(@PathVariable Long cashierId) throws Exception {
        return  ResponseEntity.ok(refundService.getRefundByCashier(cashierId));
    }


    @GetMapping("refunds/{branchId}/range")
    public ResponseEntity<List<RefundDTO>> getAllRefundsByCashierBetween(@PathVariable Long branchId
    , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) throws Exception {
        return  ResponseEntity.ok(refundService.getRefundByCashierAndDateRange(branchId,from,to));
    }

    @GetMapping("refunds/{branchId}")
    public ResponseEntity<List<RefundDTO>> getAllRefundsByBranch(@PathVariable Long branchId) throws Exception {
        return  ResponseEntity.ok(refundService.getRefundsByBranch(branchId));
    }

    @GetMapping("refunds/{shiftReportId}")
    public ResponseEntity<List<RefundDTO>> getAllRefundsByShiftReport(@PathVariable Long shiftReportId) throws Exception {
        return  ResponseEntity.ok(refundService.getRefundByShiftReports(shiftReportId));
    }

    @GetMapping("refunds/{id}")
    public ResponseEntity<RefundDTO> getAllRefundsBId(@PathVariable Long id) throws Exception {
        return  ResponseEntity.ok(refundService.getRefundById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRefundById(@PathVariable Long id) throws Exception {

        refundService.deleteRefund(id);
        return  ResponseEntity.ok(ApiResponse.builder().message("Refund Deleted Successfully").build());
    }



}
