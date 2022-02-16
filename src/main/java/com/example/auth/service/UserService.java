package com.example.auth.service;

import com.example.auth.dto.UserDto;
import com.example.auth.model.User;

import java.util.List;

public interface UserService {
    User register(User user);
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);
    void delete(Long id);
}