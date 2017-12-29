package com.hw.service.impl;

import com.hw.domain.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@ContextHierarchy(
//        {@ContextConfiguration(locations = "springContext.xml"),
//        @ContextConfiguration(classes = )}
//)
@ContextHierarchy(
        {@ContextConfiguration(locations = "classpath:springContext.xml")}
)
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void createEmptyOrder2IsNotNull() throws Exception {
        Assert.assertNotNull(orderService.createEmptyOrder2());
    }

    @Test
    public void createEmptyOrder2AreNotEquals() throws Exception {
        Order emptyOrder1 = orderService.createEmptyOrder2();
        Order emptyOrder2 = orderService.createEmptyOrder2();
        Assert.assertNotSame(emptyOrder1, emptyOrder2);
    }
}