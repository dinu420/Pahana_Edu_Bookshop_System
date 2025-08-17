package com.pahanasystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.pahanasystem.dao.impl.BillDaoImpl;
import com.pahanasystem.model.Bill;

public class BillDaoTest {

	@Test
	void testFindAllForCustomer() {
	    BillDao billDao = new BillDaoImpl();

	    String accountNo = "C001";
	    String billNo1 = "BILL_024";
	    String billNo2 = "BILL_025";

	    // Save first bill
	    Bill bill1 = new Bill();
	    bill1.setBillNo(billNo1);
	    bill1.setAccountNo(accountNo);
	    bill1.setUnits(20);
	    bill1.setAmount(new BigDecimal("200"));
	    bill1.setGeneratedBy("cashier1");
	    bill1.setIssuedAt(LocalDateTime.now());
	    billDao.save(bill1);

	    // Save second bill
	    Bill bill2 = new Bill();
	    bill2.setBillNo(billNo2);
	    bill2.setAccountNo(accountNo);
	    bill2.setUnits(35);
	    bill2.setAmount(new BigDecimal("350"));
	    bill2.setGeneratedBy("cashier1");
	    bill2.setIssuedAt(LocalDateTime.now());
	    billDao.save(bill2);

	    // Fetch all bills for this customer
	    List<Bill> bills = billDao.findAllForCustomer(accountNo);

	    assertTrue(!bills.isEmpty(), "Customer should have at least one bill");
	    assertTrue(bills.stream().anyMatch(b -> b.getBillNo().equals(billNo1)), "First bill should be found");
	    assertTrue(bills.stream().anyMatch(b -> b.getBillNo().equals(billNo2)), "Second bill should be found");
	}


}
