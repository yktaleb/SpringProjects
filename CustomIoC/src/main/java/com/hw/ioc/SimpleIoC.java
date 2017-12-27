package com.hw.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleIoC {

    private Config config;
    private Map<String, Object> container;

    public SimpleIoC(Config config) {
        this.config = config;
        container = new HashMap<>();
    }

    public List<String> beanDefinitions() {
        return config.beanNames();
    }

    public Object getBean(String beanName) throws Exception{

        if (container.containsKey(beanName)) {
            return container.get(beanName);
        }

        Object instanceOfBean = createBean(beanName);
        checkNullBean(instanceOfBean);
        callInitMethod(instanceOfBean);
        Object newBean = createBenchmarkProxy(instanceOfBean);
        container.put(beanName, newBean);
        return newBean;
    }

    private Object createBenchmarkProxy(Object instanceOfBean) {
        if (hasNoAnnotatedBenchmarkMethod(instanceOfBean.getClass())) {
            return instanceOfBean;
        }

        return Proxy.newProxyInstance(
                instanceOfBean.getClass().getClassLoader(),
                instanceOfBean.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (instanceOfBeanHasAnnotation(method, Benchmark.class)) {
                            return performBenchmarkOperation(method, args, instanceOfBean);
                        }
                        return method.invoke(instanceOfBean, args);
                    }

                    private boolean instanceOfBeanHasAnnotation(Method method, Class<? extends Annotation> annotation) throws NoSuchMethodException {
                        Class<?> beanClass = instanceOfBean.getClass();
                        Method beanMethod = beanClass.getMethod(method.getName(), method.getParameterTypes());
                        return beanMethod.isAnnotationPresent(annotation);
                    }

                    private Object performBenchmarkOperation(Method method, Object[] args, Object instanceOfBean) throws Throwable {
                        long startTime = System.nanoTime();
                        Object invoke = method.invoke(instanceOfBean, args);
                        long endTime = System.nanoTime();
                        long operationTime = (endTime - startTime) / 1000;
                        System.out.println("Method operation time = " + operationTime + " micro seconds.");
                        return invoke;
                    }
                });
    }

    private boolean hasNoAnnotatedBenchmarkMethod(Class<?> beanClass) {
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Benchmark.class)){
                return false;
            }
        }
        return true;
    }

    private void callInitMethod(Object bean) throws Exception{
        Method initMethod = getInitMethod(bean);
        if(initMethod != null){
            initMethod.invoke(bean);
        }
    }

    private Method getInitMethod(Object bean) {
        try {
            return bean.getClass().getMethod("init");
        } catch (NoSuchMethodException e){
            return null;
        }
    }

    private Object createBean(String beanName) {
        try {
            Class<?> classOfBean = getBeanClass(beanName);
            if (0 == classOfBean.getConstructors().length) {
                return classOfBean.newInstance();
            }

            Constructor<?> constructor = classOfBean.getConstructors()[0];
            return createBeanWithConstructor(constructor);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object createBeanWithConstructor(Constructor<?> constructor) throws Exception {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parametersForConstructor = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Object parameterObject = createParameterForConstructor(parameterTypes[i]);
            parametersForConstructor[i] = parameterObject;
        }

        return constructor.newInstance(parametersForConstructor);
    }

    private Object createParameterForConstructor(Class<?> parameterType) throws Exception {
        String parameterClassName = parameterType.getSimpleName();
        parameterClassName = parseClassName(parameterClassName);
        return getBean(parameterClassName);
    }

    private String parseClassName(String parameterClassName) {
        return Character.toLowerCase(parameterClassName.charAt(0)) + parameterClassName.substring(1);
    }

    private Class<?> getBeanClass(String beanName) {
        BeanDefinition beanDefinition = config.beanDefinition(beanName);
        return beanDefinition.getBeanClass();
    }

    private void checkNullBean(Object instanceOfBean) {
        if (instanceOfBean == null) {
            throw new IllegalArgumentException();
        }
    }
}
