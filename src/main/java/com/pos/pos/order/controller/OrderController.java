package com.pos.pos.order.controller;


import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.enums.OrderStatus;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.order.service.OrderService;
import com.pos.pos.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws UserException {
        return ResponseEntity.ok(orderService.createOrder(orderDto));

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderByBranch(@PathVariable Long branchId,
                                                          @RequestParam(required = false) Long customerId,
                                                          @RequestParam(required = false) Long cashierId,
                                                           @RequestParam(required = false) PaymentType paymentType,
    @RequestParam(required = false) OrderStatus orderStatus) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByBranch(branchId,customerId,cashierId,paymentType,orderStatus));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long cashierId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    @GetMapping("/today/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderTodayByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrderByBranch(branchId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrderCustomerId(@PathVariable Long customerId) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrders(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
    }


}

