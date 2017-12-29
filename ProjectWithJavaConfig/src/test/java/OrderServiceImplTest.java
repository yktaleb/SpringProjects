import com.hw.config.RepositoryConfig;
import com.hw.config.ServiceConfig;
import com.hw.domain.Order;
import com.hw.service.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextHierarchy(
        {
                @ContextConfiguration(classes = {ServiceConfig.class, RepositoryConfig.class,}),
        }
)
@ActiveProfiles(profiles = "test")
public class OrderServiceImplTest {

    @Autowired
    private Environment environment;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void createNewOrder() {
        System.out.println(Arrays.toString(environment.getActiveProfiles()));
        System.out.println(orderService.createOrder());
    }

    @IfProfileValue(name = "test", value = "test")
    @Test
    public void createEmptyOrder2IsNotNull() throws Exception {
        Assert.assertNotNull(orderService.createEmptyOrder());
    }

    @Test
    @Repeat(10)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createEmptyOrder2AreNotEquals() throws Exception {
        Order emptyOrder1 = orderService.createEmptyOrder();
        Order emptyOrder2 = orderService.createEmptyOrder();
        Assert.assertNotSame(emptyOrder1, emptyOrder2);
    }
}