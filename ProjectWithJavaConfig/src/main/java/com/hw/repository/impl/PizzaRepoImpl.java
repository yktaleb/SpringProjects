package com.hw.repository.impl;

import com.hw.domain.Pizza;
import com.hw.domain.PizzaType;
import com.hw.repository.PizzaRepo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class PizzaRepoImpl implements PizzaRepo {

    private List<Pizza> allPizzas = new ArrayList<>();

    @Override
    public List<Pizza> allPizzas() {
        return allPizzas;
    }

    @Override
    @PostConstruct
    public void init() {
        allPizzas.add(new Pizza(1L, PizzaType.ITALIAN, "NewItalian", 200.98d));
        allPizzas.add(new Pizza(1L, PizzaType.UKRAINIAN, "NewUkrainian", 150.50d));
    }
}
