package com.hw.repository;

import com.hw.domain.Pizza;

import java.util.List;

public interface PizzaRepo {
    List<Pizza> allPizzas();
    void init();
}

