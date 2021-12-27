package com.example.notepad.dao;

import com.example.notepad.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    Optional<Customer> getCustomerByEmail(String email);

    void deleteCustomerByEmail(String email);

    Page<Customer> getCustomers(Pageable pageable);

    Page<Customer> getCustomersByEnabledIsTrue(Pageable pageable);

}
