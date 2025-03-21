package com.airline.Airline.services;

import java.util.List;

import com.airline.Airline.dto.LoginRequest;
import com.airline.Airline.models.User;

public interface UserService {
    User createUser(User user);
    User createAdmin(User user);
    String loginUser(LoginRequest user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}