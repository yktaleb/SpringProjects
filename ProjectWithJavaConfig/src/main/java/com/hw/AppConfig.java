package com.hw;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public static SomeBean someBean() {
        System.out.println("someBean");
        return new SomeBean();
    }

    @Bean
    public static AnotherBean anotherBean() {
        System.out.println("anotherBean");
        return new AnotherBean(someBean());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        //название соответствует имени метода
//        SomeBean someBean = (SomeBean) context.getBean("beanSome");
//
//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
//        System.out.println(context.getBeanFactory().getBeanDefinition("beanSome"));

        AnotherBean anotherBean1 = (AnotherBean) context.getBean("anotherBean");
        SomeBean someBean1 = anotherBean1.someBean;
        AnotherBean anotherBean2 = (AnotherBean) context.getBean("anotherBean");
        SomeBean someBean2 = anotherBean2.someBean;

        System.out.println(someBean1 == someBean2);
    }
}
