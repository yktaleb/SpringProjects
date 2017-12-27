package com.hw.repository;

import com.hw.domain.Order;

import java.util.List;

public interface OrderRepo {
    List<Order> allOrders();

    void addNewOrder(Order order);

    Long getLastId();
}
