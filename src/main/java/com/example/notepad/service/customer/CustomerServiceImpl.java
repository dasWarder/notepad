package com.example.notepad.service.customer;

import com.example.notepad.dao.CustomerRepository;
import com.example.notepad.model.Customer;
import com.example.notepad.service.exception.role.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public Customer saveCustomer(Customer customer) {

    log.info("In CustomerServiceImpl.saveCustomer -  Save a new customer");
    return customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomer(Long id, Customer customer) throws CustomerNotFoundException {

    log.info("In CustomerServiceImpl.updateCustomer - Update customer by id = {}", id);

    if (!customerRepository.existsById(id)) {

      log.error("In CustomerServiceImpl.updateCustomer - CustomerNotFound exception occurred");
      throw new CustomerNotFoundException(
          String.format("Customer with id = %d not found exception", id));
    }
    customer.setId(id);

    return customerRepository.save(customer);
  }

  @Override
  public Customer getCustomerById(Long id) throws CustomerNotFoundException {

    log.info("In CustomerServiceImpl.getCustomerById - Get customer by id = {}", id);

    return customerRepository
        .findById(id)
        .orElseThrow(
            () ->
                new CustomerNotFoundException(
                    String.format("Customer with id = %d not found", id)));
  }

  @Override
  public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {

    log.info("In CustomerServiceImpl.getCustomerByEmail - Get customer by email = {}", email);

    return customerRepository
        .getCustomerByEmail(email)
        .orElseThrow(
            () ->
                new CustomerNotFoundException(
                    String.format("Customer with email = %s not found", email)));
  }

  @Override
  public void deleteCustomerById(Long id) {

    log.info("In CustomerServiceImpl.deleteCustomerById - Delete customer by id = {}", id);
    customerRepository.deleteById(id);
  }

  @Override
  public void deleteCustomerByEmail(String email) {

    log.info("In CustomerServiceImpl.deleteCustomerByEmail - Delete customer by email = {}", email);
    customerRepository.deleteCustomerByEmail(email);
  }

  @Override
  public Page<Customer> getCustomers(Pageable pageable) {

    log.info("In CustomerServiceImpl.getCustomers - Get customers");
    return customerRepository.getCustomers(pageable);
  }

  @Override
  public Page<Customer> getEnabledCustomers(Pageable pageable) {

    log.info("In CustomerServiceImpl.getEnabledCustomers - Get collection of enabled customers");
    return customerRepository.getCustomersByEnabledIsTrue(pageable);
  }
}
