package com.hw.web.infrastructure;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private Map<String, MyController> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put("/hello", new HelloController());
        controllerMap.put("/pizza", new PizzaController());
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
        return controllerMap.get(controllerName);
    }

    private String getControllerNameFromRequest(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(requestURI.lastIndexOf('/'));
    }

}
