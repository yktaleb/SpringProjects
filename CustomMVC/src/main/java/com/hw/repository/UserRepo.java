package com.hw.repository;

import com.hw.domain.User;

import java.util.List;

public interface UserRepo {
    List<User> allUsers();

    User getUserById(Long id);

    void init();

}
