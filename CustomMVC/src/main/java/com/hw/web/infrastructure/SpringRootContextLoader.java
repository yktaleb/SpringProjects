package com.hw.web.infrastructure;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpringRootContextLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<String> rootContexts = getContexts(servletContextEvent);
        System.out.println(rootContexts);

        AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext();
        for (Class<?> context : getConfigs(rootContexts)) {
            rootContext.register(context);
        }
        rootContext.refresh();

        ServletContext servletContext = servletContext(servletContextEvent);
        servletContext.setAttribute("rootContext", rootContext);
    }

    private List<Class<?>> getConfigs(List<String> contexts) {
        List<Class<?>> contextClasses = new ArrayList<>();
        for (String context : contexts) {
            try {
                contextClasses.add(Class.forName(context));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return contextClasses;
    }

    private ServletContext servletContext(ServletContextEvent servletContextEvent) {
        return servletContextEvent.getServletContext();
    }

    private List<String> getContexts(ServletContextEvent servletContextEvent) {
        return Arrays.asList(
                servletContext(servletContextEvent).getInitParameter("rootContexts").split(" "));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
