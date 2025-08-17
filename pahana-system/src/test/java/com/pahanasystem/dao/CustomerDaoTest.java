package com.pahanasystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanasystem.dao.impl.CustomerDaoImpl;
import com.pahanasystem.model.Customer;

public class CustomerDaoTest {
	
	@Test
	void testAddCustomer() {
	    CustomerDao customerDao = new CustomerDaoImpl();

	    String accountNo = "C" + System.currentTimeMillis(); 
	    String name = "TestCustomer_" + System.currentTimeMillis();
	    Customer customer = new Customer(accountNo, name, "123 Main St", "0771234567", 100);

	    customerDao.save(customer);

	    Optional<Customer> fetched = customerDao.findByAccountNo(accountNo);

	    assertTrue(fetched.isPresent(),"Customer should be inserted successfully");
	}

	@Test
	void testUpdateCustomer() {
	    CustomerDao customerDao = new CustomerDaoImpl();

	    
	    String accountNo = "C" + System.currentTimeMillis();
	    String name = "UpdateCustomer_" + System.currentTimeMillis();
	    Customer customer = new Customer(accountNo, name, "789 Pine St", "0775556666", 70);
	    customerDao.save(customer);

	   
	    customer.setName("UpdatedName");
	    customer.setUnitsConsumed(100);
	    customerDao.update(customer);

	
	    Optional<Customer> fetched = customerDao.findByAccountNo(accountNo);

	 
	    assertTrue(fetched.isPresent(), "Customer should exist after update");
	    assertTrue(fetched.get().getName().equals("UpdatedName"), "Customer name should be updated");
	    assertTrue(fetched.get().getUnitsConsumed() == 100, "Units consumed should be updated");
	}

	
	
	@Test
	void testDeleteCustomer() {
	    CustomerDao customerDao = new CustomerDaoImpl();


	    String accountNo = "C" + System.currentTimeMillis();
	    String name = "DeleteCustomer_" + System.currentTimeMillis();
	    Customer customer = new Customer(accountNo, name, "101 Maple St", "0773334444", 80);
	    customerDao.save(customer);

	    customerDao.delete(accountNo);

	
	    Optional<Customer> fetched = customerDao.findByAccountNo(accountNo);

	  
	    assertTrue(fetched.isEmpty(), "Deleted customer should not be found");
	}



}
