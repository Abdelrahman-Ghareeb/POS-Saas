package com.pos.pos.customer.service.impl;

import com.pos.pos.customer.entity.Customer;
import com.pos.pos.customer.repo.CustomerRepo;
import com.pos.pos.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepo customerRepo;
    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws Exception {
        return customerRepo.findById(id).orElseThrow(() -> new Exception("Customer not found with id: " + id));
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {

        Customer customer1 = customerRepo.findById(id).orElseThrow(() -> new Exception("Customer not found with id: " + id));
        customer1.setFullName(customer.getFullName());
        customer1.setEmail(customer.getEmail());
        customer1.setPhone(customer.getPhone());
        return customerRepo.save(customer1);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new Exception("Customer not found with id: " + id));
        customerRepo.delete(customer);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> searchCustomersByName(String key) {
        return customerRepo.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(key,key);
    }
}
