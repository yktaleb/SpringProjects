package com.hw.service;

import com.hw.domain.Order;
import com.hw.domain.Pizza;
import com.hw.domain.User;

import java.util.List;

public interface OrderService {
    void addNewOrder(Order order);

    void addNewOrder(User user, Pizza... pizzas);

    List<Order> getAllOrders();
}
