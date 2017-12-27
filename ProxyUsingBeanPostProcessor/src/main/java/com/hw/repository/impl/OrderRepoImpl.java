package com.hw.repository.impl;

import com.hw.domain.Order;
import com.hw.repository.OrderRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {

    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> allOrders() {
        return orders;
    }

    @Override
    public void addNewOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Long getLastId() {
        return Long.valueOf(orders.size() - 1);
    }


}
