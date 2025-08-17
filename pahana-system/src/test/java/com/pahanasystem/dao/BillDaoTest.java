package com.pahanasystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanasystem.dao.impl.BillDaoImpl;
import com.pahanasystem.dao.impl.UserDaoImpl;
import com.pahanasystem.model.Bill;
import com.pahanasystem.model.User;

public class BillDaoTest {

    @Test
    void testSaveBill() {
        BillDao billDao = new BillDaoImpl();

        
        Bill bill = new Bill();
        bill.setBillNo("BILL_" + System.currentTimeMillis());
        bill.setAccountNo("C001");
        bill.setUnits(50);
        bill.setAmount(new BigDecimal("500"));
        bill.setGeneratedBy("cashier1");
        bill.setIssuedAt(LocalDateTime.now());

        
        billDao.save(bill);

       
        Optional<Bill> fetched = billDao.findByBillNo(bill.getBillNo());

        
        assertTrue(fetched.isPresent(), "Bill should be saved and found successfully");
    }
    
    @Test
    void testFindBillByBillNo() {
        UserDao userDao = new UserDaoImpl();
        String uniqueUsername = "cashier_" + System.currentTimeMillis();
        userDao.save(new User(uniqueUsername, "password123", "cashier"));

        BillDao billDao = new BillDaoImpl();

       
        String billNo = "BILL_" + System.currentTimeMillis();
        Bill bill = new Bill();
        bill.setBillNo(billNo);
        bill.setAccountNo("C001"); 
        bill.setUnits(30);
        bill.setAmount(new BigDecimal("300"));
        bill.setGeneratedBy(uniqueUsername);
        bill.setIssuedAt(LocalDateTime.now());

        billDao.save(bill);

       
        Optional<Bill> fetched = billDao.findByBillNo(billNo);

    
        assertTrue(fetched.isPresent(),"Bill should be found by bill number");
    }

}
