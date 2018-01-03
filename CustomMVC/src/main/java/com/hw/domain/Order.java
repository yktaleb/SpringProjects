package com.hw.domain;

import java.util.List;

public class Order {
    private Long id;
    private List<Pizza> pizzas;
    private User user;
    private double price;

    public Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    public Order(Long id, List<Pizza> pizzas, User user, double price) {
        this.id = id;
        this.pizzas = pizzas;
        this.user = user;
        this.price = price;
    }

    public Order(Long id, List<Pizza> pizzas, User user) {
        this.id = id;
        this.pizzas = pizzas;
        this.user = user;
        this.price = price;
    }

    public Double getTotalPrice() {
        return pizzas.stream()
                .map(pizza -> pizza.getPrice())
                .reduce((price1, price2) -> price1 + price2).get();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", user=" + user +
                ", price=" + price +
                '}';
    }
}
