package com.hw.service.impl;

import com.hw.domain.User;
import com.hw.repository.UserRepo;
import com.hw.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.allUsers();
    }
}
