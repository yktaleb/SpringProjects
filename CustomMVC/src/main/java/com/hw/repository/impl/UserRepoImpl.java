package com.hw.repository.impl;

import com.hw.domain.User;
import com.hw.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private List<User> allUsers = new ArrayList<>();

    @Override
    public List<User> allUsers() {
        return allUsers;
    }

    @Override
    public User getUserById(Long id) {
        return allUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst().get();
    }

    @Override
    public void init() {
        allUsers.add(new User(1L, "Yarik"));
        allUsers.add(new User(2L, "Dima"));
    }
}
