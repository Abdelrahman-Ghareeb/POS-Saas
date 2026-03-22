package com.pos.pos.customer.controller;

import com.pos.pos.customer.entity.Customer;
import com.pos.pos.customer.service.CustomerService;
import com.pos.pos.store.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

     @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }

     @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) throws Exception {
         return ResponseEntity.ok(customerService.getCustomerById(id));
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable long id) throws Exception {
         customerService.deleteCustomer(id);
         return ResponseEntity.ok(ApiResponse.builder().message("Customer Deleted Successfully").build());
     }

     @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
         return ResponseEntity.ok(customerService.getAllCustomers());
     }
     @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String key) throws Exception {
         return ResponseEntity.ok(customerService.searchCustomersByName(key));
     }
}
