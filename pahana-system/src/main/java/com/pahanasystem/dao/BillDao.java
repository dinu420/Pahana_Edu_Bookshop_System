package com.pahanasystem.dao;

import com.pahanasystem.model.Bill;
import java.util.List;
import java.util.Optional;

public interface BillDao {
    void save(Bill bill);

    Optional<Bill> findByBillNo(String billNo);
    List<Bill> findAllForCustomer(String accountNo);
}
