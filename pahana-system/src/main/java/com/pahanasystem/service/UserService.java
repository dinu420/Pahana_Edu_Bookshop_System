package com.pahanasystem.service;

import com.pahanasystem.model.User;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Optional<User> findUserByUsername(String username);
}