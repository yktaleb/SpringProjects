package com.hw.config;

import com.hw.service.PizzaService;
import com.hw.web.infrastructure.HelloController;
import com.hw.web.infrastructure.MyController;
import com.hw.web.infrastructure.PizzaController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public MyController hello() {
        return new HelloController();
    }

    @Bean
    public MyController pizzas(PizzaService pizzaService) {
        return new PizzaController(pizzaService);
    }
}
