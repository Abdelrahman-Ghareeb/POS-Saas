package com.pos.pos.shiftReport.service.impl;

import com.pos.pos.order.entity.Order;
import com.pos.pos.order.entity.OrderItem;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.order.repo.OrderRepo;
import com.pos.pos.product.entity.Product;
import com.pos.pos.refund.entity.Refund;
import com.pos.pos.refund.repository.RefundRepository;
import com.pos.pos.shiftReport.dto.ShiftReportDTO;
import com.pos.pos.shiftReport.entity.PaymentSummary;
import com.pos.pos.shiftReport.entity.ShiftReport;
import com.pos.pos.shiftReport.mapper.ShiftReportMapper;
import com.pos.pos.shiftReport.repo.ShiftReportRepository;
import com.pos.pos.shiftReport.service.ShiftReportService;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepo orderRepo;
    @Override
    public ShiftReportDTO startShift() throws Exception {

        User currentUser= userService.getCurrentUser();

        LocalDateTime startShift = LocalDateTime.now();
        LocalDateTime startOfDay= startShift.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = startShift.withHour(23).withMinute(59).withMinute(59);

        Optional<ShiftReport> existing= shiftReportRepository.findByCashierAndShiftStartBetween(currentUser,startOfDay,endOfDay);
        if(existing.isPresent()){
            throw new Exception("Shift Already Started");

        }

        ShiftReport shiftReport= ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(startShift)
                .branch(currentUser.getBranch())

                .build();


        return ShiftReportMapper.mapToDto(shiftReportRepository.save(shiftReport));
    }

    @Override
    public ShiftReportDTO endShift(Long shiftId, LocalDateTime endShift) throws Exception {

        User currentUser= userService.getCurrentUser();

        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser).orElseThrow(()-> new Exception("Shift Not Found "));

        shiftReport.setShiftEnd(endShift);
        List<Refund> refunds=refundRepository.findByCashierIdAndCreatedAtBetween(currentUser.getId(),shiftReport.getShiftStart(),shiftReport.getShiftEnd());

       double totalRefunds = refunds.stream().mapToDouble( re -> re.getAmount() != null ?re.getAmount():0.0).sum();

        List<Order> orders=orderRepo.findByCashierAndCreatedAtBetween(currentUser,shiftReport.getShiftStart(),shiftReport.getShiftEnd());

        double totalSales= orders.stream().mapToDouble(or ->or.getTotalAmount()).sum();
        int totalOrders = orders.size();
        double netSales=totalSales-totalRefunds;


        shiftReport.setNetSales(netSales);
        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaryList(getPaymentSummary(orders,totalSales));
        shiftReport.setReturnsOrders(refunds);



        return ShiftReportMapper.mapToDto(shiftReportRepository.save(shiftReport));
    }


    @Override
    public ShiftReportDTO getShiftReportById(Long shiftReportId) throws Exception {
       ShiftReport shiftReport= shiftReportRepository.findById(shiftReportId).orElseThrow(() -> new Exception("Shift Report With The Given Id Not Found"));
        return ShiftReportMapper.mapToDto(shiftReport);
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return shiftReportRepository.findAll().stream().map(ShiftReportMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReportsByBranchIs(Long branchId) {
        return shiftReportRepository.findByBranchId(branchId).stream().map(ShiftReportMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReportsByCashierId(Long cashierId) {
        return shiftReportRepository.findByCashierId(cashierId).stream().map(ShiftReportMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getCurrentShiftInProgress(Long cashierId) throws Exception {
        User currentUser= userService.getCurrentUser();
        ShiftReport shiftReport=shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser).orElseThrow(
                () -> new Exception("No Active Shift Found for Cashier")
        );

        LocalDateTime now = LocalDateTime.now();
        List<Order> orders= orderRepo.findByCashierAndCreatedAtBetween(currentUser,shiftReport.getShiftStart(),now);


        List<Refund> refunds=refundRepository.findByCashierIdAndCreatedAtBetween(currentUser.getId(),shiftReport.getShiftStart(),now);

        double totalRefunds = refunds.stream().mapToDouble( re -> re.getAmount() != null ?re.getAmount():0.0).sum();


        double totalSales= orders.stream().mapToDouble(or ->or.getTotalAmount()).sum();
        int totalOrders = orders.size();
        double netSales=totalSales-totalRefunds;


        shiftReport.setNetSales(netSales);
        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaryList(getPaymentSummary(orders,totalSales));
        shiftReport.setReturnsOrders(refunds);

        return ShiftReportMapper.mapToDto(shiftReportRepository.save(shiftReport));
    }

    @Override
    public ShiftReportDTO getShiftByCashierANdDate(Long cashierId, LocalDateTime dateTime) throws Exception {

        User user = userService.getUserById(cashierId);
        LocalDateTime startOfDay= dateTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = dateTime.withHour(23).withMinute(59).withMinute(59);

        ShiftReport shiftReport = shiftReportRepository.findByCashierAndShiftStartBetween(user,startOfDay,endOfDay).orElseThrow(()-> new Exception("Shift Report Not found"));


        return ShiftReportMapper.mapToDto(shiftReport);
    }




    private List<PaymentSummary> getPaymentSummary(List<Order> orders, double totalSales) {

        Map<PaymentType,List<Order>> grouped= orders.stream().collect(Collectors.groupingBy( x ->x.getPaymentType() != null ?x.getPaymentType(): PaymentType.CASH));

        List<PaymentSummary> paymentSummaryList= new ArrayList<>();

        for ( Map.Entry<PaymentType,List<Order>>  entry:grouped.entrySet()){
            double amount = entry.getValue().stream().mapToDouble(Order::getTotalAmount).sum();
            int transactions= entry.getValue().size();
            double percentage=(amount/totalSales)*100;
            PaymentSummary paymentSummary= new PaymentSummary();
            paymentSummary.setPercentage(percentage);
            paymentSummary.setTotalAmount(amount);
            paymentSummary.setTransactionCount(transactions);

            paymentSummaryList.add(paymentSummary);

        }

        return paymentSummaryList;

    }

    private List<Product> getTopSellingProducts(List<Order> orders) {

        Map<Product,Integer> productSellMap = new HashMap<>();

        for(Order order: orders){
            for(OrderItem orderItem:order.getItems()){
                Product product = orderItem.getProduct();
                productSellMap.put(product,productSellMap.getOrDefault(product,0)+orderItem.getQuantity());
            }
        }

        return productSellMap.entrySet().stream().sorted((a,b) -> a.getValue().compareTo(b.getValue()))
                .limit(5)
                .map(Map.Entry::getKey).collect(Collectors.toList());

    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream().sorted(Comparator.comparing(Order::getCreatedAt).reversed()).limit(5).collect(Collectors.toList());
    }


}
