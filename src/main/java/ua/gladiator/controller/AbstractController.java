package ua.gladiator.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {
    void processRequest(HttpServletRequest req, HttpServletResponse resp, String page) throws IOException, ServletException {
        System.out.println("prekol");
        if (page.contains("redirect")){
            resp.sendRedirect(page.replace("redirect/", ""));
        }else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
