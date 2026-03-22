package com.pos.pos.customer.service;

import com.pos.pos.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer) throws Exception;
    Customer getCustomerById(Long id) throws Exception;
    Customer updateCustomer(Long id, Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    List<Customer> getAllCustomers();
    List<Customer> searchCustomersByName(String key);
}
