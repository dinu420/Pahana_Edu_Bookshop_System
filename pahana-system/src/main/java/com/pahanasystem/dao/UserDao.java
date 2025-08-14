package com.pahanasystem.dao;

import com.pahanasystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User user);
    Optional<User> findByUsername(String username);
    boolean deleteByUsername(String username);
    List<User> getUsersByRole(String role);
}
