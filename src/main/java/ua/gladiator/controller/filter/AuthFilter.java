package ua.gladiator.controller.filter;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger log = LogManager.getLogger(AuthFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Cache-control", "no-cache, no-store, must-revalidate");

        String path = req.getRequestURI().replace("/lib/", "");

        User currentUser = (User) req.getSession().getAttribute("user");
        if (path.startsWith("reader")) {
            if (currentUser != null && currentUser.getRole().equals(Role.READER)) {
                chain.doFilter(request,response);
                return;
            }
            req.getRequestDispatcher("deny.jsp").forward(request, response);


        }
        else if (path.startsWith("admin")) {
            if (currentUser != null && currentUser.getRole().equals(Role.ADMIN)) {
                chain.doFilter(request,response);
                return;
            }
            req.getRequestDispatcher("deny.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
