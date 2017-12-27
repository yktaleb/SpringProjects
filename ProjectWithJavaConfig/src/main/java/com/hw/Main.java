package com.hw;

import com.hw.config.RepositoryConfig;
import com.hw.config.ServiceConfig;
import com.hw.domain.PizzaType;
import com.hw.service.OrderService;
import com.hw.service.PizzaService;
import com.hw.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext repoContext =
                new AnnotationConfigApplicationContext(RepositoryConfig.class);

        AnnotationConfigApplicationContext serviceContext =
                new AnnotationConfigApplicationContext();
        serviceContext.setParent(repoContext);
        serviceContext.register(ServiceConfig.class);
        serviceContext.refresh();

        PizzaService pizzaService = (PizzaService) serviceContext.getBean("pizzaService");
        OrderService orderService = (OrderService) serviceContext.getBean("orderService");
        UserService userService = (UserService) serviceContext.getBean("userService");

        System.out.println(Arrays.toString(serviceContext.getBeanDefinitionNames()));

        System.out.println(orderService.getAllOrders());

        orderService.addNewOrder(userService.getUserById(1L), pizzaService.getPizzasByType(PizzaType.ITALIAN).get(0),
                pizzaService.getPizzasByType(PizzaType.UKRAINIAN).get(0));

        System.out.println(orderService.getAllOrders());
    }
}
