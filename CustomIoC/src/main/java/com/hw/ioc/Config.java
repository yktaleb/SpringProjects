package com.hw.ioc;

import java.util.List;

public interface Config {

    List<String> beanNames();

    BeanDefinition beanDefinition(String beanName);
}