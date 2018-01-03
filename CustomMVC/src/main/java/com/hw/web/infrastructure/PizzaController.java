package com.hw.web.infrastructure;

import com.hw.domain.Pizza;
import com.hw.service.PizzaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PizzaController implements MyController {

    private PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()) {
            writer.print("<b>Pizzas</b>");
            for (Pizza pizza : pizzaService.getAllPizza()) {
                writer.print(pizza);
            }
        }
    }
}
