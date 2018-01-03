package com.hw.web.infrastructure;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyController {
    void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
