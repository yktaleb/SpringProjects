package com.hw.config;

import com.hw.repository.OrderRepo;
import com.hw.repository.PizzaRepo;
import com.hw.repository.UserRepo;
import com.hw.repository.impl.OrderRepoImpl;
import com.hw.repository.impl.PizzaRepoImpl;
import com.hw.repository.impl.UserRepoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean(initMethod = "init")
    public UserRepo userRepo() {
        return new UserRepoImpl();
    }

    @Bean
    public OrderRepo orderRepo() {
        return new OrderRepoImpl();
    }

    @Bean(initMethod = "init")
    public PizzaRepo pizzaRepo() {
        return new PizzaRepoImpl();
    }
}
