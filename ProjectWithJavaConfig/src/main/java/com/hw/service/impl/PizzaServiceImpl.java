package com.hw.service.impl;

import com.hw.domain.Pizza;
import com.hw.domain.PizzaType;
import com.hw.repository.PizzaRepo;
import com.hw.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaServiceImpl implements PizzaService {
    private PizzaRepo pizzaRepo;

    public PizzaServiceImpl(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public List<Pizza> getPizzasByType(PizzaType type) {
        return pizzaRepo.allPizzas().stream()
                .filter(pizza -> type.equals(pizza.getPizzaType()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pizza> getAllPizza() {
        return pizzaRepo.allPizzas();
    }

    @Override
    public List<Pizza> getPizzaByName(String name) {
        return pizzaRepo.allPizzas().stream()
                .filter(pizza -> name.equals(pizza.getTitle()))
                .collect(Collectors.toList());
    }
}
