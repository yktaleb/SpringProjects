import com.hw.domain.PizzaType;
import com.hw.service.OrderService;
import com.hw.service.PizzaService;
import com.hw.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("springContext.xml");

        PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
        OrderService orderService = (OrderService) context.getBean("orderService");
        UserService userService = (UserService) context.getBean("userService");


        System.out.println(orderService.getAllOrders());

        orderService.addNewOrder(userService.getUserById(1L), pizzaService.getPizzasByType(PizzaType.ITALIAN).get(0),
                pizzaService.getPizzasByType(PizzaType.UKRAINIAN).get(0));

        System.out.println(orderService.getAllOrders());

    }
}
