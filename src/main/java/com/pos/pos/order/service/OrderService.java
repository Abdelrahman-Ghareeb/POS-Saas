package com.pos.pos.order.service;

import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.entity.Order;
import com.pos.pos.order.enums.OrderStatus;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.user.exception.UserException;

import java.util.List;

public interface OrderService {

     OrderDto createOrder(OrderDto orderDto) throws UserException;
     OrderDto getOrderById(Long id) throws Exception;
     List<OrderDto> getOrderByBranch(Long branch, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus);
     List<OrderDto> getOrderByCashier(long cashier);
     void deleteOrder(Long orderId) throws Exception;

     List<OrderDto> getTodayOrderByBranch(long branchId);
     List<OrderDto> getOrderByCustomerId(Long customerId);
     List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId);
}
