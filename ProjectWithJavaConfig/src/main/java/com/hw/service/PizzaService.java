package com.hw.service;

import com.hw.domain.Pizza;
import com.hw.domain.PizzaType;

import java.util.List;

public interface PizzaService {
    List<Pizza> getPizzasByType(PizzaType type);

    List<Pizza> getAllPizza();

    List<Pizza> getPizzaByName(String name);
}
