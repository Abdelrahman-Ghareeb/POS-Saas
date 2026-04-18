package com.pos.pos.refund.service.impl;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.order.entity.Order;
import com.pos.pos.order.repo.OrderRepo;
import com.pos.pos.refund.dto.RefundDTO;
import com.pos.pos.refund.entity.Refund;
import com.pos.pos.refund.mapper.RefundMapper;
import com.pos.pos.refund.repository.RefundRepository;
import com.pos.pos.refund.service.RefundService;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.repo.UserRepository;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {


    private final RefundRepository refundRepository;
    private final UserService userService;
    private final OrderRepo orderRepo;
    @Override
    public RefundDTO createRefund(RefundDTO refund) throws Exception {

        User cashier = userService.getCurrentUser();

        Order order= orderRepo.findById(refund.getOrderId()).orElseThrow( ()-> new Exception("Order Not Found"));
        Branch branch = order.getBranch();

        Refund createdRefund= Refund.builder()
                .branch(branch)
                .order(order)
                .cashier(cashier)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();
        Refund savedRefund = refundRepository.save(createdRefund);
        return RefundMapper.mapToDTO(refundRepository.save(savedRefund));
    }

    @Override
    public List<RefundDTO> listAllRefunds() {
        return refundRepository.findAll().stream().map( RefundMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByShiftReports(Long ShiftReportId) {
        return refundRepository.findByShiftReportId(ShiftReportId).stream().map(RefundMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) {


        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId,startDate,endDate).stream().map(RefundMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundsByBranch(Long branchId) {
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long id) {
        return RefundMapper.mapToDTO(refundRepository.findById(id).get());
    }

    @Override
    public void deleteRefund(Long id) {
        this.getRefundById(id);
        refundRepository.deleteById(id);

    }
}
