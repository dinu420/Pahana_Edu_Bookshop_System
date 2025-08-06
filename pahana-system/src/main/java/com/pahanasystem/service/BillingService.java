package com.pahanasystem.service;

import com.pahanasystem.model.Bill;
import com.pahanasystem.model.Customer;

import java.util.List;
import java.util.Optional;

public interface BillingService {
    Bill generateBill(String accountNo, int unitsConsumed, String generatedBy);
    Optional<Bill> findBillByBillNo(String billNo);
    List<Bill> getBillsForCustomer(String accountNo);
    Optional<Customer> findCustomer(String accountNo);
}