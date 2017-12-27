package com.hw.domain;

public class Pizza {
    private Long id;
    private PizzaType pizzaType;
    private String title;
    private Double price;

    public Pizza(Long id, PizzaType pizzaType, String title, Double price) {
        this.id = id;
        this.pizzaType = pizzaType;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", pizzaType=" + pizzaType +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
