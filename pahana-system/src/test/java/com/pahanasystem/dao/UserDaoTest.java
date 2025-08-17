package com.pahanasystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.pahanasystem.dao.impl.UserDaoImpl;
import com.pahanasystem.model.User;

import java.util.List;
import java.util.Optional;

public class UserDaoTest {

    @Test
    void testSaveUser() {
        
        UserDao userDao = new UserDaoImpl(); 

        String uniqueUsername = "terry_" + System.currentTimeMillis();
        User user = new User(uniqueUsername, "hashedPwd123", "cashier");

        userDao.save(user);

        Optional<User> fetched = userDao.findByUsername(uniqueUsername);
        assertTrue(fetched.isPresent(),"User should be inserted successfully");
    }
    
    @Test
    void testFindUserByUsername() {
        UserDao userDao = new UserDaoImpl();


        String uniqueUsername = "alex_" + System.currentTimeMillis();
        User user = new User(uniqueUsername, "password123", "cashier");
        userDao.save(user);


        Optional<User> fetched = userDao.findByUsername(uniqueUsername);
        assertTrue(fetched.isPresent(), "User should be found by username");
    }

    
    @Test
    void testDeleteUserByUsername() {
        UserDao userDao = new UserDaoImpl();


        String uniqueUsername = "mike_" + System.currentTimeMillis();
        User user = new User(uniqueUsername, "pass123", "cashier");
        userDao.save(user);

  
        boolean deleted = userDao.deleteByUsername(uniqueUsername);
        assertTrue(deleted, "User should be deleted successfully");

        
        Optional<User> fetched = userDao.findByUsername(uniqueUsername);
        assertTrue(fetched.isEmpty(),"Deleted user should be not found");
    }
    
    @Test
    void testGetAllCashiers() {
        UserDao userDao = new UserDaoImpl();

        
        String cashier1Username = "cashier1_" + System.currentTimeMillis();
        String cashier2Username = "cashier2_" + System.currentTimeMillis();
        String adminUsername = "admin_" + System.currentTimeMillis();

      
        User cashier1 = new User(cashier1Username, "pass1", "cashier");
        User cashier2 = new User(cashier2Username, "pass2", "cashier");
        User admin = new User(adminUsername, "pass3", "admin");

       
        userDao.save(cashier1);
        userDao.save(cashier2);
        userDao.save(admin);

       
        List<User> cashiers = userDao.getUsersByRole("cashier");

        
        assertTrue(cashiers.size() >= 2, "There should be at least 2 cashiers");
        assertTrue(cashiers.stream().allMatch(u -> u.getRole().equalsIgnoreCase("cashier")),
                   "All returned users should have role 'cashier'");
    }

    
    

}
