package com.hw.service.impl;

import com.hw.domain.Order;
import com.hw.domain.Pizza;
import com.hw.domain.User;
import com.hw.repository.OrderRepo;
import com.hw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void addNewOrder(Order order) {
        orderRepo.addNewOrder(order);
    }

    @Override
    public void addNewOrder(User user, Pizza... pizzas) {
        Order order = createOrder();
        order.setId(orderRepo.getLastId() + 1);
        order.setPizzas(Arrays.asList(pizzas));
        order.setUser(user);
        order.setPrice(order.getTotalPrice());
        orderRepo.addNewOrder(order);
    }

    public Order createOrder() {
        return null;
    }

    public Order createEmptyOrder() {
        return new Order();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.allOrders();
    }
}
