package com.pos.pos.order.service.impl;

import com.pos.pos.branch.entity.Branch;
import com.pos.pos.order.dto.OrderDto;
import com.pos.pos.order.entity.Order;
import com.pos.pos.order.entity.OrderItem;
import com.pos.pos.order.enums.OrderStatus;
import com.pos.pos.order.enums.PaymentType;
import com.pos.pos.order.mapper.OrderMapper;
import com.pos.pos.order.repo.OrderRepo;
import com.pos.pos.order.service.OrderService;
import com.pos.pos.product.entity.Product;
import com.pos.pos.product.repo.ProductRepo;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;


    @Override
    public OrderDto createOrder(OrderDto orderDto) throws UserException {

        User cashier = userService.getCurrentUser();

        Branch branch = cashier.getBranch();
        if(branch == null){
            throw  new UserException("Cashier's Branch not found");
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .paymentType(orderDto.getPaymentType())

                .build();

        List<OrderItem> items=orderDto.getItems().stream().map(
                itemDto ->{
                    Product product = productRepo.findById(itemDto.getProductId()).orElseThrow(
                            () -> new EntityNotFoundException("Product Not Found"));

                            return OrderItem.builder()
                                    .product(product)
                                    .order(order)
                                    .quantity(itemDto.getQuantity())
                                    .price(product.getSellingPrice()*itemDto.getQuantity())
                                    .build();


                }
        ).toList();

        double total = items.stream().mapToDouble(
                OrderItem::getPrice
        ).sum();
        order.setTotalAmount(total);
        order.setItems(items);

        Order savedOrder=orderRepo.save(order);
        return OrderMapper.mapToDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return orderRepo.findById(id).map(OrderMapper::mapToDto).orElseThrow(
                ()-> new Exception("The provided Id Not Found ")
        );
    }

    @Override
    public List<OrderDto> getOrderByBranch(Long branch, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) {
        return orderRepo.findByBranchId(branch).stream()
                .filter( order -> customerId==null || (order.getCustomer() != null && order.getCustomer().getId().equals(cashierId)))
                .filter(order -> cashierId ==null || (order.getCashier()!= null && order.getCashier().getId().equals(cashierId)))
                .filter(order -> paymentType ==null || order.getPaymentType()==paymentType)
                .map(OrderMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(long cashier) {
        return orderRepo.findByCashierId(cashier).stream().map(OrderMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) throws Exception {

        Order order = orderRepo.findById(orderId).orElseThrow(()-> new Exception("The Provided Order Id Not found"));
        orderRepo.delete(order);

    }

    @Override
    public List<OrderDto> getTodayOrderByBranch(long branchId) {

        LocalDate today= LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();
        return orderRepo.findByBranchIdAndCreatedAtBetween(branchId,start,end).stream().map(OrderMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCustomerId(Long customerId) {
        return  orderRepo.findByCustomerId(customerId).stream().map(OrderMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) {
        return orderRepo.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::mapToDto).collect(Collectors.toList());
    }
}
