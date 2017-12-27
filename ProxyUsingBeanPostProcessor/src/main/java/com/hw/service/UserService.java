package com.hw.service;

import com.hw.domain.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
}
