package com.pahanasystem.service;

import com.pahanasystem.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void registerCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String accountNo);
    Optional<Customer> findCustomerByAccountNo(String accountNo);
    List<Customer> getAllCustomers();
}
