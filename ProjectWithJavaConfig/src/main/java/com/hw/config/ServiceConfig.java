package com.hw.config;

import com.hw.domain.Order;
import com.hw.repository.OrderRepo;
import com.hw.repository.PizzaRepo;
import com.hw.repository.UserRepo;
import com.hw.service.OrderService;
import com.hw.service.PizzaService;
import com.hw.service.UserService;
import com.hw.service.impl.OrderServiceImpl;
import com.hw.service.impl.PizzaServiceImpl;
import com.hw.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {

    @Bean
    @Scope("prototype")
    public Order order() {
        return new Order();
    }

    @Bean
    public UserService userService(UserRepo userRepo) {
        return new UserServiceImpl(userRepo);
    }

    @Bean
    public PizzaService pizzaService(PizzaRepo pizzaRepo) {
        return new PizzaServiceImpl(pizzaRepo);
    }

    @Bean
    public OrderService orderService(OrderRepo orderRepo) {
        return new OrderServiceImpl(orderRepo) {
            @Override
            public Order createOrder() {
                return order();
            }
        };
    }

}
