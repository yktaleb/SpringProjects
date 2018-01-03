package com.hw.web.infrastructure;

import com.hw.config.WebConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private AnnotationConfigApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        Class<?> config = getConfig();
        webContext = new AnnotationConfigApplicationContext();
        webContext.register(WebConfig.class);
        webContext.refresh();
    }

    private Class<?> getConfig() {
        String contextLocation = getServletConfig().getInitParameter("contextLocation");
        try {
            return Class.forName(contextLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void destroy() {
        webContext.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        MyController controller = getController(req);
        controller.handleRequest(req, resp);
    }

    private MyController getController(HttpServletRequest req) {
        String controllerName = getControllerNameFromRequest(req);
        return webContext.getBean(controllerName, MyController.class);
    }

    private String getControllerNameFromRequest(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(requestURI.lastIndexOf('/') + 1);
    }

}
