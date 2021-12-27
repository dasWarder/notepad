package com.example.notepad.service.customer;

import com.example.notepad.model.Customer;
import com.example.notepad.service.exception.role.CustomerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

  Customer saveCustomer(Customer customer);

  Customer updateCustomer(Long id, Customer customer) throws CustomerNotFoundException;

  Customer getCustomerById(Long id) throws CustomerNotFoundException;

  Customer getCustomerByEmail(String email) throws CustomerNotFoundException;

  void deleteCustomerById(Long id);

  void deleteCustomerByEmail(String email);

  Page<Customer> getCustomers(Pageable pageable);

  Page<Customer> getEnabledCustomers(Pageable pageable);
}
