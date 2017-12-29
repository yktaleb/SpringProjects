package com.hw.service.impl;

import com.hw.config.Benchmark;
import com.hw.domain.Order;
import com.hw.domain.Pizza;
import com.hw.domain.User;
import com.hw.repository.OrderRepo;
import com.hw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;

    public OrderServiceImpl() {
    }

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void addNewOrder(Order order) {
        orderRepo.addNewOrder(order);
    }

    @Override
    @Benchmark
    public void addNewOrder(User user, Pizza... pizzas) {
        Order order = new Order(orderRepo.getLastId() + 1, Arrays.asList(pizzas), user);
        order.setPrice(order.getTotalPrice());
        orderRepo.addNewOrder(order);
    }

    public static Order createEmptyOrder() {
        return new Order();
    }

    public Order createEmptyOrder2() {
        return new Order();
    }

    public Order createEmptyOrder3(String string) {
        return new Order();
    }

    @Override
    @Benchmark
    public List<Order> getAllOrders() {
        return orderRepo.allOrders();
    }
}
