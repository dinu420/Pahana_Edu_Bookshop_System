package com.pahanasystem.dao;

import com.pahanasystem.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    void save(Customer c);
    void update(Customer c);
    void delete(String accountNo);
    Optional<Customer> findByAccountNo(String accountNo);
    List<Customer> findAll();
	String getLastAccountNo();
}
