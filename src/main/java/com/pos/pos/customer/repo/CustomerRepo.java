package com.pos.pos.customer.repo;

import com.pos.pos.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo  extends JpaRepository<Customer,Long> {

    List<Customer> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String key,String email);
}
