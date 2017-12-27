package com.hw.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (hasAnnotatedBenchmarkMethod(beanClass)) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (instanceOfBeanHasAnnotation(method, Benchmark.class)) {
                                return performBenchmarkOperation(method, args, bean);
                            }
                            return method.invoke(bean, args);
                        }

                        private boolean instanceOfBeanHasAnnotation(Method method, Class<? extends Annotation> annotation) throws NoSuchMethodException {
                            Class<?> beanClass = bean.getClass();
                            Method beanMethod = beanClass.getMethod(method.getName(), method.getParameterTypes());
                            return beanMethod.isAnnotationPresent(annotation);
                        }

                        private Object performBenchmarkOperation(Method method, Object[] args, Object instanceOfBean) throws Throwable {
                            long startTime = System.nanoTime();
                            Object invoke = method.invoke(instanceOfBean, args);
                            long endTime = System.nanoTime();
                            long operationTime = (endTime - startTime) / 1000;
                            System.out.println("Executing " + method.getName() + " time = " + operationTime + " micro seconds.");
                            return invoke;
                        }
                    });
        } else {
            return bean;
        }
    }
    private boolean hasAnnotatedBenchmarkMethod(Class<?> beanClass) {
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Benchmark.class)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
